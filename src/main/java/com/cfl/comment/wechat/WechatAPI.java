package com.cfl.comment.wechat;


import com.cfl.comment.WebUtil;

/**
 * Created by Wang Genshen on 2017-07-04.
 */
public class WechatAPI {

    public static final String APP_ID = "wx6a9926ec04c21d7e";
    public static final String APP_KEY = "XinX6SMJajtWFb0A91hWasFgWybpRVXCVrSA7rIAc30";
    public static final String CORP_ID = "wx6a9926ec04c21d7e";
    public static final String MCH_ID = "1359941402";
    public static final String API_KEY = "Livzondiagnosticslivzon1998a1989";
    public static final String MD5 = "MD5";
    public static final String TRADE_JSAPI = "JSAPI";
    public static final String REDIRECT_URL = "http://cz.livzondiagnostics.com/login/login";
    public static final String ACCESS_LOGIN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=" + WebUtil.encodeUrl(REDIRECT_URL) + "&response_type=code&scope=snsapi_privateinfo&agentid=1000004&state=access#wechat_redirect";
    public static final String GET_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + APP_ID + "&corpsecret=" + APP_KEY;
    public static final String GET_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={ACCESS_TOKEN}&code={CODE}";
    public static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String EMPINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token={ACCESS_TOKEN}";
    public static final String NOTIFY_URL = "http://cz.livzondiagnostics.com/pay/result";
    public static final String GETOPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token={ACCESS_TOKEN}";
    public static final String NOTIFY_RESULT = "<xml>" +
            "  <return_code><![CDATA[SUCCESS]]></return_code>" +
            "  <return_msg><![CDATA[OK]]></return_msg>" +
            "</xml>";
}
