package com.cfl.serviceimpl;

import com.cfl.dao.UsersDAO;
import com.cfl.service.UsersService;
import com.cfl.vo.UsersVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDAO usersDAO;

    @Override
    public void updateMoney(long id, BigDecimal money) {
        usersDAO.updateMoney(id,money);
    }

    @Override
    public int checkName(String userName) {
        return usersDAO.checkName(userName);
    }

    @Override
    public UsersVo getUser(String userName) {
        return usersDAO.getUser(userName);
    }

    @Override
    public UsersVo  queryByCardId(String CardId){
        return usersDAO.queryByCardId(CardId);
    }

    @Override
    public UsersVo queryByUserId(String openId) {
        return usersDAO.queryByUserId(openId);
    }

    @Override
    public void updateCard(UsersVo usersVo) {
        usersDAO.updateCard(usersVo);
    }

    @Override
    public int checkUserId(String userid) {
        return usersDAO.checkUserId(userid);
    }

    @Override
    public UsersVo queryByOpenId(String openid) {
        return usersDAO.queryByOpenId(openid);
    }

    @Override
    public void updateOpenId(UsersVo usersVo) {
        usersDAO.updateOpenId(usersVo);
    }

    @Override
    public UsersVo queryById(Long id) {
        return usersDAO.queryById(id);
    }

    @Override
    public void update(UsersVo usersVo) {
        usersDAO.update(usersVo);
    }

    @Override
    public void add(UsersVo usersVo) {
        usersDAO.add(usersVo);
    }

    @Override
    public void delete(Long id) {
        usersDAO.delete(id);
    }

    @Override
    public List<UsersVo> pagelist() {
        return usersDAO.pagelist();
    }

    @Override
    public int count(String userCard) {
        return usersDAO.count(userCard);
    }
}
