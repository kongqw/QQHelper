package com.kongqw.qqhelper.utils

import android.util.Log
import com.kongqw.qqhelper.QQHelper

internal object Logger {

    fun i(tag: String, log: String?) {
        if (QQHelper.IS_LOGGABLE) {
            Log.d(tag, log ?: "")
        }
    }

//    fun d(log: String?) {
//        if (QQHelper.IS_LOGGABLE) {
//            Log.d("Logger", log ?: "")
//        }
//    }
}