package com.cfl.service;

import com.cfl.vo.UsersVo;

import java.math.BigDecimal;

public interface UsersService extends BaseService<UsersVo> {
    void updateMoney(long id, BigDecimal money);
    int checkName(String userName);
    UsersVo getUser(String userName);
    UsersVo queryByCardId(String CardId);
    UsersVo queryByUserId(String userId);
    void updateCard(UsersVo usersVo);
    int checkUserId(String userid);
    UsersVo queryByOpenId(String openid);
    void updateOpenId(UsersVo usersVo);
}
