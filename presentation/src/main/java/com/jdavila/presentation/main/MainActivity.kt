package com.jdavila.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jdavila.presentation.R
import com.jdavila.presentation.databinding.ActivityMainBinding
import com.jdavila.presentation.util.UpdateChecker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        mainViewModel.checkForUpdate(UpdateChecker.getAppVersionName(this))
        setupObservers()
    }

    // region Private Methods
    private fun setupObservers() {
        val isUpdateRequiredObserver = Observer<Boolean> { updateRequired ->
            if(updateRequired) showUpdateDialog(true)
        }

        val isUpdateRecommendedObserver = Observer<Boolean> { updateRecommended ->
            if(updateRecommended) showUpdateDialog(false)
        }

        mainViewModel.isUpdateRequired.observe(this, isUpdateRequiredObserver)
        mainViewModel.isUpdateRecommended.observe(this, isUpdateRecommendedObserver)
    }

    private fun showUpdateDialog(isUpdatedRequired: Boolean) {
        val dialogBuilder = MaterialAlertDialogBuilder(this)
            .setTitle("Update application")
            .setMessage("An update is available for this app.")
            .setPositiveButton("Update") { dialogInterface, i ->
                dialogInterface.dismiss()
                Log.d("TODO", "Send user to app store using update url in remote config")
            }

        if(isUpdatedRequired) {
            dialogBuilder.setCancelable(false)
        } else {
            dialogBuilder.setCancelable(true)
            dialogBuilder.setNegativeButton("Not now") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        }
        dialogBuilder.show()
    }
    // endregion
}