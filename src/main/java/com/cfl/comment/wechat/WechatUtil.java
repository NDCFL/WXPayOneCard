package com.cfl.comment.wechat;


import com.alibaba.fastjson.JSONObject;
import com.cfl.comment.Constants;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wang Genshen on 2017-07-27.
 */
public class WechatUtil {

    /**
     * 微信授权登录
     * @param code
     * @return
     */
    public String authLogin(String code) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 微信授权登录API
        HttpGet httpGet = new HttpGet(WechatAPI.GET_ACCESS_TOKEN_URL.replace("{CODE}", code));
        String accessor = null;
        try {
            accessor = httpclient.execute(httpGet, responseHandler);
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessor;
    }

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param
     * @return
     */
    public String getEmpAllInfo(String accessToken,String user_ticket) throws  Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(WechatAPI.EMPINFO.replace("{ACCESS_TOKEN}", accessToken));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_ticket",user_ticket);
        ByteArrayEntity entity =   new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(entity);
        String result = httpclient.execute(httpPost,responseHandler);
        return result;
    }

    /**
     *
     * @param accessToken
     * @param userid
     * @return
     * @throws Exception
     */
    public String getOpenId(String accessToken,String userid) throws  Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(WechatAPI.GETOPENID.replace("{ACCESS_TOKEN}", accessToken));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",userid);
        ByteArrayEntity entity =   new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(entity);
        String result = httpclient.execute(httpPost,responseHandler);
        return result;
    }
    /**
     *
     */
    public String getUserInfo(String accessToken,String code) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(WechatAPI.GET_USER_INFO.replace("{ACCESS_TOKEN}", accessToken).replace("{CODE}",code));
        String userInfo = null;
        try {
            userInfo = httpclient.execute(httpGet, responseHandler);
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    /**
     * 预支付请求并获取预支持结果
     * @param openid
     * @param ip
     * @param body
     * @param
     * @return
     */
    public Map<String, String> prepayResult(String openid, String ip, String body, BigDecimal totalFee) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(WechatAPI.ORDER_URL);
        httpPost.addHeader("Content-Type", "text/xml");
        Map<String, String> reqData = prepayData(openid, ip, body,  totalFee);
        try {
            String data = WXPayUtil.mapToXml(reqData);
            StringEntity stringEntity = new StringEntity(data, Constants.DEFAULT_ENCODING);
            httpPost.setEntity(stringEntity);
            String result = httpclient.execute(httpPost, responseHandler);
            if (result != null) {
                result = new String(result.getBytes(Constants.ISO_ENCODING), Constants.DEFAULT_ENCODING);
            }
            Map<String, String> prepayData = WXPayUtil.xmlToMap(result); // 获取预支付结果
            httpclient.close();
            return prepayData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 准备预支付需要提交的数据
     * @param
     * @param openid
     * @param ip
     * @return
     */
    public Map<String, String> prepayData(String openid, String ip, String body, BigDecimal totalFee) {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("appid", WechatAPI.APP_ID);
        reqData.put("body", body);
        reqData.put("mch_id", WechatAPI.MCH_ID);
        reqData.put("nonce_str", WXPayUtil.generateUUID());
        reqData.put("notify_url", WechatAPI.NOTIFY_URL);
        reqData.put("sign_type", WXPayConstants.MD5);
        reqData.put("openid", openid);
        reqData.put("out_trade_no", WXPayUtil.generateUUID());
        reqData.put("total_fee", Integer.parseInt(totalFee+"")*100+"");
        reqData.put("trade_type", WechatAPI.TRADE_JSAPI);
        reqData.put("spbill_create_ip", ip);
        try {
            reqData.put("sign", WXPayUtil.generateSignature(reqData, WechatAPI.API_KEY, WXPayConstants.SignType.MD5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reqData;
    }

    /**
     * 准备支付需要提交的数据
     * @param prepayResult
     * @return
     */
    public Map<String, String> payData(Map<String, String> prepayResult) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("appId", WechatAPI.APP_ID);
        data.put("package", "prepay_id=" + prepayResult.get("prepay_id"));
        data.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
        data.put("nonceStr", WXPayUtil.generateUUID());
        data.put("signType", WXPayConstants.MD5);
        try {
            data.put("paySign", WXPayUtil.generateSignature(data, WechatAPI.API_KEY, WXPayConstants.SignType.MD5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 读取由微信传回的支付结果
     * @param request
     * @return
     */
    public Map<String, String> payResult(HttpServletRequest request) {
        try {
            ServletInputStream in = request.getInputStream();
            byte[] bytes = new byte[1024];
            int total = 0;
            StringBuffer result = new StringBuffer();
            while ((total = in.read(bytes)) != -1) {
                result.append(new String(bytes, 0, total));
            }
            System.out.println(result);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result.toString());
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 响应微信支付结果
     * @param response
     */
    public void responsePayNotify(HttpServletResponse response) {
        response.setContentType("text/xml;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(WechatAPI.NOTIFY_RESULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(final HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };
}
