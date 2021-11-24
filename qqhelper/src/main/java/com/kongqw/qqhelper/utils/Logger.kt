package com.kongqw.qqhelper.utils

import android.util.Log
import com.kongqw.qqhelper.QQHelper

object Logger {

    fun d(log: String?) {
        if (QQHelper.IS_LOGGABLE) {
            Log.d("Logger", log ?: "")
        }
    }
}