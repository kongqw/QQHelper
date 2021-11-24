package com.kongqw.qqhelper.login

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kongqw.qqhelper.QQHelper
import com.kongqw.qqhelper.login.bean.QQLoginInfo
import com.kongqw.qqhelper.utils.Logger
import com.tencent.connect.UserInfo
import com.tencent.connect.common.Constants
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import org.json.JSONObject

class QQLoginActivity : AppCompatActivity() {

    private var mQQLoginInfo: QQLoginInfo? = null
    private val mOnLoginListener = object : IUiListener {
        /**
         * 登录成功
         */
        override fun onComplete(p0: Any?) {
            try {
                val jsonObject = p0 as JSONObject

                mQQLoginInfo?.access_token = if (jsonObject.has("access_token")) jsonObject.getString("access_token") else ""
                mQQLoginInfo?.expires_in = if (jsonObject.has("expires_in")) jsonObject.getString("expires_in") else ""
                mQQLoginInfo?.openid = if (jsonObject.has("openid")) jsonObject.getString("openid") else ""

                if (!mQQLoginInfo?.access_token.isNullOrEmpty() && !mQQLoginInfo?.expires_in.isNullOrEmpty()) {
                    mTencent?.setAccessToken(mQQLoginInfo?.access_token, mQQLoginInfo?.expires_in)
                }
                if (!mQQLoginInfo?.openid.isNullOrEmpty()) {
                    mTencent?.openId = mQQLoginInfo?.openid
                }

                val userInfo = UserInfo(applicationContext, mTencent?.qqToken)
                userInfo.getUserInfo(mOnGetUserInfoListener)
            } catch (e: Exception) {
                e.printStackTrace()
                QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginFail()
            }
        }

        /**
         * 登录取消
         */
        override fun onCancel() {
            Logger.d("onQQAuthLoginCancel")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginCancel()
            finish()
        }

        override fun onWarning(p0: Int) {
            Logger.d("onWarning()")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginCancel()
            finish()
        }

        /**
         * 登录失败
         */
        override fun onError(p0: UiError?) {
            Logger.d("onQQAuthLoginError p0 = $p0")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginError(p0?.errorCode, p0?.errorMessage, p0?.errorDetail)
            finish()
        }
    }

    private val mOnGetUserInfoListener = object : IUiListener {
        /**
         * 登录成功
         */
        override fun onComplete(p0: Any?) {
            try {
                Logger.d("mOnGetUserInfoListener onComplete getUserInfo = $p0")
                val jsonObject = p0 as JSONObject

                mQQLoginInfo?.ret = jsonObject.getInt("ret")
                mQQLoginInfo?.msg = jsonObject.getString("msg")
                mQQLoginInfo?.is_lost = jsonObject.getInt("is_lost")
                mQQLoginInfo?.nickname = jsonObject.getString("nickname")
                mQQLoginInfo?.gender = jsonObject.getString("gender")
                mQQLoginInfo?.province = jsonObject.getString("province")
                mQQLoginInfo?.city = jsonObject.getString("city")
                mQQLoginInfo?.year = jsonObject.getString("year")
                mQQLoginInfo?.constellation = jsonObject.getString("constellation")
                mQQLoginInfo?.figureurl = jsonObject.getString("figureurl")
                mQQLoginInfo?.figureurl_1 = jsonObject.getString("figureurl_1")
                mQQLoginInfo?.figureurl_2 = jsonObject.getString("figureurl_2")
                mQQLoginInfo?.figureurl_qq = jsonObject.getString("figureurl_qq")
                mQQLoginInfo?.figureurl_qq_1 = jsonObject.getString("figureurl_qq_1")
                mQQLoginInfo?.figureurl_qq_2 = jsonObject.getString("figureurl_qq_2")
                mQQLoginInfo?.figureurl_type = jsonObject.getString("figureurl_type")
                mQQLoginInfo?.is_yellow_vip = jsonObject.getString("is_yellow_vip")
                mQQLoginInfo?.vip = jsonObject.getString("vip")
                mQQLoginInfo?.yellow_vip_level = jsonObject.getString("yellow_vip_level")
                mQQLoginInfo?.level = jsonObject.getString("level")
                mQQLoginInfo?.is_yellow_year_vip = jsonObject.getString("is_yellow_year_vip")

                QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginSuccess(mQQLoginInfo)
            } catch (e: Exception) {
                e.printStackTrace()
                QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginFail()
            }
            finish()
        }

        /**
         * 登录取消
         */
        override fun onCancel() {
            Logger.d("onQQAuthLoginCancel")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginCancel()
            finish()
        }

        /**
         * 警告
         */
        override fun onWarning(p0: Int) {
            Logger.d("onWarning($p0)")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginWarning(p0)
            finish()
        }

        /**
         * 登录失败
         */
        override fun onError(p0: UiError?) {
            Logger.d("onQQAuthLoginError p0 = $p0")
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginError(p0?.errorCode, p0?.errorMessage, p0?.errorDetail)
            finish()
        }
    }


    private var mTencent: Tencent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mQQLoginInfo = QQLoginInfo()

        val qqAppId = getAppMetaData(applicationContext, "qq_app_id")

        mTencent = Tencent.createInstance(qqAppId, applicationContext)
        Logger.d("QQAppId = $qqAppId")

        val login = mTencent?.login(this, "all", mOnLoginListener)
        Logger.d("login = $login")
        QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginStart()
    }

    override fun onDestroy() {
        QQHelper.mOnQQAuthLoginListener = null
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logger.d("onActivityResult requestCode = $requestCode  resultCode = $resultCode")
        if (resultCode != Constants.ACTIVITY_OK) {
            // 取消了
            super.onActivityResult(requestCode, resultCode, data)
            QQHelper.mOnQQAuthLoginListener?.onQQAuthLoginCancel()
            finish()
            return
        }
        if (requestCode == Constants.REQUEST_LOGIN && resultCode == Constants.ACTIVITY_OK) {
            Tencent.handleResultData(data, mOnLoginListener)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    /**
     * 读取AppMetaData信息
     */
    private fun getAppMetaData(context: Context, key: String): String? {
        if (key.isEmpty()) {
            return null
        }
        return try {
            context.packageManager?.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)?.metaData?.get(key)?.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}
