package com.kongqw.qqhelper.share

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kongqw.qqhelper.QQHelper
import com.kongqw.qqhelper.utils.Logger
import com.kongqw.qqhelper.utils.MetaUtil
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError

class QQShareActivity : AppCompatActivity(), IUiListener {

    private var mTencent: Tencent? = null

    companion object {
        private const val EXTRA_SHARE_PARAMS = "EXTRA_SHARE_PARAMS"

        fun share(context: Context, bundle: Bundle) {
            context.startActivity(Intent(context, QQShareActivity::class.java).putExtra(EXTRA_SHARE_PARAMS, bundle))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTencent = Tencent.createInstance(MetaUtil.getQQAppId(applicationContext), applicationContext)

        val params = intent.getBundleExtra(EXTRA_SHARE_PARAMS)
        mTencent?.shareToQQ(this, params, this)

        QQHelper.mQQUiListener?.onQQShareStart()
    }

    override fun onDestroy() {
        QQHelper.mQQUiListener = null
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logger.d("requestCode = $requestCode  resultCode = $resultCode")
        Tencent.onActivityResultData(requestCode, resultCode, data, this)
    }

    /**
     * 分享完成
     */
    override fun onComplete(p0: Any?) {
        Logger.d("分享完成")
        QQHelper.mQQUiListener?.onQQShareComplete(p0)
        finish()
    }

    /**
     * 分享取消
     */
    override fun onCancel() {
        Logger.d("分享取消")
        QQHelper.mQQUiListener?.onQQShareCancel()
        finish()
    }

    /**
     * 分享异常
     */
    override fun onError(p0: UiError?) {
        Logger.d("分享异常 errorCode = ${p0?.errorCode}  errorMessage = ${p0?.errorMessage}  errorDetail = ${p0?.errorDetail}")
        QQHelper.mQQUiListener?.onQQShareError(p0?.errorCode, p0?.errorMessage, p0?.errorDetail)
        finish()
    }

}
