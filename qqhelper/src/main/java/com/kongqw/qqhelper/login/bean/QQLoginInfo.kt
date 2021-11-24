package com.kongqw.qqhelper.login.bean

import java.io.Serializable

class QQLoginInfo : Serializable {

    var ret: Int? = null
    var msg: String? = null

    var openid: String? = null
    var access_token: String? = null
    var pay_token: String? = null
    var expires_in: String? = null
    var pf: String? = null
    var pfkey: String? = null
    var login_cost: Int? = null
    var query_authority_cost: Int? = null
    var authority_cost: Int? = null

    var is_lost: Int? = null
    var nickname: String? = null
    var gender: String? = null
    var province: String? = null
    var city: String? = null
    var year: String? = null
    var constellation: String? = null
    var figureurl: String? = null
    var figureurl_1: String? = null
    var figureurl_2: String? = null
    var figureurl_qq: String? = null
    var figureurl_qq_1: String? = null
    var figureurl_qq_2: String? = null
    var figureurl_type: String? = null
    var is_yellow_vip: String? = null
    var vip: String? = null
    var yellow_vip_level: String? = null
    var level: String? = null
    var is_yellow_year_vip: String? = null

    override fun toString(): String {
        return "QQLoginInfo(ret=$ret, msg=$msg, openid=$openid, access_token=$access_token, pay_token=$pay_token, expires_in=$expires_in, pf=$pf, pfkey=$pfkey, login_cost=$login_cost, query_authority_cost=$query_authority_cost, authority_cost=$authority_cost, is_lost=$is_lost, nickname=$nickname, gender=$gender, province=$province, city=$city, year=$year, constellation=$constellation, figureurl=$figureurl, figureurl_1=$figureurl_1, figureurl_2=$figureurl_2, figureurl_qq=$figureurl_qq, figureurl_qq_1=$figureurl_qq_1, figureurl_qq_2=$figureurl_qq_2, figureurl_type=$figureurl_type, is_yellow_vip=$is_yellow_vip, vip=$vip, yellow_vip_level=$yellow_vip_level, level=$level, is_yellow_year_vip=$is_yellow_year_vip)"
    }
}