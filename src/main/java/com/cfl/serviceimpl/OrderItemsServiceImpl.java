package com.cfl.serviceimpl;

import com.cfl.dao.OrderItemsDAO;
import com.cfl.service.OrderItemsService;
import com.cfl.vo.OrderItemsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Resource
    private OrderItemsDAO orderItemsDAO;

    @Override
    public OrderItemsVo queryById(Long id) {
        return orderItemsDAO.queryById(id);
    }

    @Override
    public void update(OrderItemsVo orderItemsVo) {
        orderItemsDAO.update(orderItemsVo);
    }

    @Override
    public void add(OrderItemsVo orderItemsVo) {
        orderItemsDAO.add(orderItemsVo);
    }

    @Override
    public void delete(Long id) {
        orderItemsDAO.delete(id);
    }

    @Override
    public List<OrderItemsVo> pagelist() {
        return orderItemsDAO.pagelist();
    }

    @Override
    public int count(String userCard) {
        return orderItemsDAO.count(userCard);
    }

    @Override
    public List<OrderItemsVo> findList(String userCard) {
        return orderItemsDAO.findList(userCard);
    }

    @Override
    public List<OrderItemsVo> listPage(int startPage, int pageSize, String cardId) {
        return orderItemsDAO.listPage(startPage,pageSize,cardId);
    }
}
