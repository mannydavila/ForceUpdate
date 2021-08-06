package com.jdavila.presentation.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


class UpdateChecker {

    companion object {
        fun getAppVersionName(context: Context) : String? {
            var version: String? = null
            try {
                val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.getPackageName(), 0)
                version = pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return version
        }
    }
}