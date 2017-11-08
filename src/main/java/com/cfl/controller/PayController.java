package com.cfl.controller;

import com.cfl.comment.wechat.WechatAPI;
import com.cfl.comment.wechat.WechatUtil;
import com.cfl.service.OrderItemsService;
import com.cfl.service.UsersService;
import com.cfl.vo.OrderItemsVo;
import com.cfl.vo.UsersVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("pay")
public class PayController {
    @Resource
    private UsersService usersService;
    @Resource
    private OrderItemsService orderItemsService;
    @RequestMapping("index1")
    public  String  payIndex() throws IOException, ServletException {
        return "pay";
    }
    @RequestMapping("index")
    public String pay(HttpServletRequest req, HttpServletResponse resp, HttpSession session, BigDecimal userMoney,long id) throws IOException, ServletException {
        if (userMoney != BigDecimal.valueOf(0)) {
            UsersVo usersVo = usersService.queryById(id);
            WechatUtil wechatUtil = new WechatUtil();
            Map<String, String> prepayResult = wechatUtil.prepayResult(usersVo.getOpenid(), req.getRemoteAddr(), "一卡通充值-微信支付",userMoney);
            // 正式付款需要提交的数据
            Map<String, String> payData = wechatUtil.payData(prepayResult);
            req.setAttribute("appId", WechatAPI.APP_ID);
            req.setAttribute("timeStamp", payData.get("timeStamp"));
            req.setAttribute("nonceStr", payData.get("nonceStr"));
            req.setAttribute("packages", payData.get("package"));
            req.setAttribute("paySign", payData.get("paySign"));
            req.setAttribute("userMoney", userMoney);
       }
        return "qrpay"; // 预支付数据转发到页面，调用js支付
    }
    @RequestMapping("result")
    public void result(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("***********************notify_url*************************");
        WechatUtil wechatUtil = new WechatUtil();
        Map<String, String> resultMap = wechatUtil.payResult(req);
        try {
            String resultCode = resultMap.get("result_code");
            if (resultCode != null && resultCode.equals("SUCCESS")) {
                ServletContext servletContext = req.getServletContext();
                String openId = resultMap.get("openid");
                UsersVo usersVo = usersService.queryByOpenId(openId);
                OrderItemsVo orderItemsVo = new OrderItemsVo();
                orderItemsVo.setUserCard(usersVo.getUserCard());
                orderItemsVo.setMoney(BigDecimal.valueOf(Integer.parseInt(resultMap.get("total_fee"))));
                orderItemsVo.setStatus(0);
                orderItemsService.add(orderItemsVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String openId = resultMap.get("openid");
            UsersVo usersVo = usersService.queryByOpenId(openId);
            OrderItemsVo orderItemsVo = new OrderItemsVo();
            orderItemsVo.setUserCard(usersVo.getUserCard());
            orderItemsVo.setMoney(BigDecimal.valueOf(Integer.parseInt(resultMap.get("total_fee"))));
            orderItemsVo.setStatus(1);
            orderItemsService.add(orderItemsVo);
        }
        wechatUtil.responsePayNotify(resp);
    }
}
