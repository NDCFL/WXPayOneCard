package com.cfl.controller;

import com.cfl.comment.PagingBean;
import com.cfl.service.OrderItemsService;
import com.cfl.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RequestMapping("items")
@Controller
public class OrderItemsController {
    @Resource
    private OrderItemsService orderItemsService;
    @Resource
    private UsersService usersService;
    @RequestMapping("myItems/{cardId}")
    @ResponseBody
    public PagingBean myItems(int pageSize, int pageIndex, @PathVariable("cardId") String cardId) throws  Exception{
        PagingBean pagingBean = new PagingBean();
        pagingBean.setTotal(orderItemsService.count(cardId));
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pagingBean.setrows(orderItemsService.listPage(pagingBean.getStartIndex(),pagingBean.getPageSize(),cardId));
        return pagingBean;
    }
    @RequestMapping("itemsPage/{cardId}")
    @ResponseBody
    public ModelAndView itemsPage(@PathVariable("cardId") String cardId) throws  Exception{
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("myItems");
        modelAndView.addObject("cardId",cardId);
        modelAndView.addObject("userVo",usersService.queryByCardId(cardId));
        return modelAndView;
    }
}
