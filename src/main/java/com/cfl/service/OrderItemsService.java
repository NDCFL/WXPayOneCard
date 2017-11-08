package com.cfl.service;

import com.cfl.vo.OrderItemsVo;

import java.util.List;

public interface OrderItemsService  extends BaseService<OrderItemsVo> {
    List<OrderItemsVo> findList(String userCard);
    List<OrderItemsVo> listPage(int startPage, int pageSize, String cardId);
}
