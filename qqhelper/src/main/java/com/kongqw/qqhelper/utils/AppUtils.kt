package com.kongqw.qqhelper.utils

import android.annotation.SuppressLint
import android.content.Context

internal object AppUtils {

    /**
     * 是否安装了QQ
     */
    @SuppressLint("QueryPermissionsNeeded")
    @JvmStatic
    fun isQQInstalled(context: Context): Boolean {
        try {
            context.applicationContext.packageManager.getInstalledPackages(0).forEach { packageInfo ->
                if ("com.tencent.mobileqq" == packageInfo.packageName) {
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}

