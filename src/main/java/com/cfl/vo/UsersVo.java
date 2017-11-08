package com.cfl.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UsersVo implements Serializable {
    private long id;
    private String userName;
    private String userCard;
    private BigDecimal userMoney;
    private String headIcon;
    private String phone;
    private String userid;
    private String openid;

    public UsersVo() {
    }

    public UsersVo(long id, String userName, String userCard, BigDecimal userMoney, String headIcon, String phone, String userid) {
        this.id = id;
        this.userName = userName;
        this.userCard = userCard;
        this.userMoney = userMoney;
        this.headIcon = headIcon;
        this.phone = phone;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UsersVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userCard='" + userCard + '\'' +
                ", userMoney=" + userMoney +
                ", headIcon='" + headIcon + '\'' +
                ", phone='" + phone + '\'' +
                ", userid='" + userid + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}

