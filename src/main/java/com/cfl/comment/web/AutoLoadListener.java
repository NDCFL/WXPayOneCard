package com.cfl.comment.web;


import com.cfl.comment.ConfigConstants;
import com.cfl.comment.Constants;
import com.cfl.comment.util.DateUtil;
import com.cfl.comment.util.SingletonConfig;
import com.cfl.vo.UsersVo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by WangGenshen on 5/16/16.
 */
public class AutoLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("AutoLoadListener init!");
        ServletContext servletContext = servletContextEvent.getServletContext();
        String autoloadConfig = servletContext.getInitParameter("autoloadConfigLocation");
        SingletonConfig config = SingletonConfig.getInstance();
        config.build(autoloadConfig);
        servletContext.setAttribute(ConfigConstants.PRODUCTION_ENV, config.getString(ConfigConstants.PRODUCTION_ENV));
        servletContext.setAttribute(ConfigConstants.ACTIVITY_BEGIN_TIME, DateUtil.dateToString(Calendar.getInstance().getTime()));
        servletContext.setAttribute(ConfigConstants.ACTIVITY_MAX_USER, 10000);
        servletContext.setAttribute(ConfigConstants.PRIZED_COUNT, 10);
        servletContext.setAttribute(ConfigConstants.PRIZED_COUNT_STOCK, 5);
        servletContext.setAttribute(ConfigConstants.PRIZED_USERS, "");
        servletContext.setAttribute(ConfigConstants.PRIZED_USERS_STOCK, new ArrayList<UsersVo>());
        servletContext.setAttribute(ConfigConstants.GAME_OVER, false);
        servletContext.setAttribute(ConfigConstants.ADMIN_PWD, "admin654321");
        servletContext.setAttribute(ConfigConstants.TICKET_PRICE, 300.0);

        servletContext.setAttribute(Constants.ACTUAL_PAY, 0);
        servletContext.setAttribute(Constants.PAYED_USERS, new ArrayList<UsersVo>());
        servletContext.setAttribute(Constants.TOTAL_MONEY, 0);
        servletContext.setAttribute(Constants.LOGO_IMG, "images/sing.jpg");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("AutoLoadListener destroyed!");
    }
}
