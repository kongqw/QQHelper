package com.kongqw.qqhelper.utils

import android.content.Context
import android.content.pm.PackageManager

internal object MetaUtil {

    /**
     * 读取AppMetaData信息
     */
    private fun getAppMetaData(context: Context, key: String): String? {
        if (key.isEmpty()) {
            return null
        }
        return try {
            context.packageManager?.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                ?.metaData?.get(key)?.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取QQ APP ID
     */
    fun getQQAppId(context: Context): String? {
        return getAppMetaData(context, "qq_app_id")
    }
}