package com.cfl.dao;

import com.cfl.vo.UsersVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UsersDAO extends BaseDAO<UsersVo> {
    void updateMoney(@Param("id") long id, @Param("money") BigDecimal money);
    int checkName(String userName);
    UsersVo getUser(String userName);
    UsersVo queryByCardId(String CardId);
    UsersVo queryByUserId(String userId);
    void updateCard(UsersVo usersVo);
    int checkUserId(String userid);
    UsersVo queryByOpenId(String openid);
    void updateOpenId(UsersVo usersVo);
}
