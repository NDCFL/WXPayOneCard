package com.cfl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cfl.comment.wechat.WechatUtil;
import com.cfl.service.OrderItemsService;
import com.cfl.service.UsersService;
import com.cfl.vo.OrderItemsVo;
import com.cfl.vo.UsersVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("login")
public class LoginController {
    @Resource
    private UsersService usersService;
    @Resource
    private OrderItemsService orderItemsService;

    @RequestMapping("loginPage")
    public String login() {
        return "login";
    }

    @RequestMapping("checkName")
    public ModelAndView checkName(UsersVo usersVo) {
        ModelAndView modelAndView = new ModelAndView();
        int cnt = usersService.checkName(usersVo.getUserName());
        if (cnt == 0) {
            //新增该用户
            //微信登录后获取该参数
            usersVo.setUserid("11111111");
            usersVo.setUserCard("暂缺");
            usersVo.setUserMoney(BigDecimal.valueOf(0));
            usersService.add(usersVo);
            modelAndView.addObject("userVo", usersService.getUser(usersVo.getUserName()));
        } else {
            UsersVo user = usersService.getUser(usersVo.getUserName());
            modelAndView.addObject("userVo", user);
        }
        modelAndView.setViewName("info");
        return modelAndView;
    }

    @RequestMapping("addCard")
    public ModelAndView addCard(UsersVo usersVo) {
        ModelAndView modelAndView = new ModelAndView();
        usersService.update(usersVo);
        UsersVo usersVo1 = usersService.queryById(usersVo.getId());
        modelAndView.addObject("userVo", usersVo1);
        modelAndView.setViewName("info");
        return modelAndView;
    }
    @RequestMapping("updateCard")
    public ModelAndView updateCard(UsersVo usersVo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");
        usersService.updateCard(usersVo);
        UsersVo usersVo1 = usersService.queryById(usersVo.getId());
        modelAndView.addObject("userVo", usersVo1);
        return  modelAndView;

    }
    @RequestMapping("addMoney")
    public ModelAndView addMoney(Long id, BigDecimal userMoney) {
        OrderItemsVo orderItemsVo = new OrderItemsVo();
        ModelAndView modelAndView = new ModelAndView();
        usersService.updateMoney(id, userMoney);
        UsersVo usersVo = usersService.queryById(id);
        modelAndView.addObject("userVo", usersVo);
        modelAndView.setViewName("info");
        orderItemsVo.setUserCard(usersVo.getUserCard());
        orderItemsVo.setMoney(userMoney);
        orderItemsVo.setStatus(0);
        orderItemsService.add(orderItemsVo);
        modelAndView.addObject("order", orderItemsService.findList(usersVo.getUserid()));
        return modelAndView;
    }

    //微信登录之后的返回处理
    //微信登录之后的返回处理
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        WechatUtil wechatUtil = new WechatUtil();
        //获取accessTocken
        String accesInfo = wechatUtil.authLogin(code);
        JSONObject accesTockenJSON = JSON.parseObject(accesInfo);
        String accesTocken = accesTockenJSON.get("access_token").toString();
        if (accesTocken != null) {
            String userInfo = wechatUtil.getUserInfo(accesTocken,code);
            //获取企业员工的user_ticket,如果是非企业员工，则获取到openId
            if(userInfo==null || userInfo.equals("")){
                modelAndView.setViewName("error");
                return modelAndView;
            }else {
                JSONObject jsonObject = JSON.parseObject(userInfo);
                try{
                    String ticket = jsonObject.get("user_ticket").toString();
                    //获取到企业授权登录用户的基本信息
                    String empinfo = wechatUtil.getEmpAllInfo(accesTocken,ticket);
                    JSONObject jsonObject1 = JSON.parseObject(empinfo);
                    int errcode = Integer.parseInt(jsonObject1.get("errcode").toString());
                    String userid = jsonObject1.get("userid").toString();
                    if(errcode==0){
                        String openIdInfo = wechatUtil.getOpenId(accesTocken,userid);
                        JSONObject object = JSON.parseObject(openIdInfo);
                        String openid = object.get("openid").toString();
                        //检测该用户是否授权登录过该企业号
                        int cnt = usersService.checkUserId(userid);
                        if(cnt==0){
                            //如果从未授权登录过该企业号则需要插入到数据库中
                            UsersVo user = new UsersVo();
                            user.setUserName(jsonObject1.get("name").toString());
                            user.setUserMoney((BigDecimal.valueOf(0)));
                            user.setUserCard("暂缺");
                            user.setHeadIcon(jsonObject1.get("avatar").toString());
                            user.setPhone(jsonObject1.get("mobile").toString());
                            user.setUserid(userid);
                            user.setOpenid(openid);
                            usersService.add(user);
                            modelAndView.addObject("userVo",usersService.queryByUserId(userid));
                            session.setAttribute("userVo",usersService.queryByUserId(userid));
                            modelAndView.setViewName("info");
                            return  modelAndView;
                        }else{
                            UsersVo usersVo = usersService.queryByUserId(userid);
                            if(usersVo.getOpenid()!=openid){
                                usersVo.setOpenid(openid);
                                usersVo.setId(usersVo.getId());
                                usersService.updateOpenId(usersVo);
                            }
                            modelAndView.addObject("userVo", usersService.queryByUserId(userid));
                            modelAndView.setViewName("info");
                            return modelAndView;
                        }
                    }else {
                        modelAndView.setViewName("error");
                        return modelAndView;
                    }
                }catch (Exception e){
                    modelAndView.setViewName("error");
                    return modelAndView;
                }
            }
        } else {
            modelAndView.setViewName("/error");
            return  modelAndView;
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
