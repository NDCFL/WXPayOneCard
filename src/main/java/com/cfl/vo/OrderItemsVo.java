package com.cfl.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderItemsVo implements Serializable {
    private long id;
    private String userCard;
    private BigDecimal money;
    private int status;
    private Date createTime;

    public OrderItemsVo() {
    }

    public OrderItemsVo(long id, String userCard, BigDecimal money, int status, Date createTime) {
        this.id = id;
        this.userCard = userCard;
        this.money = money;
        this.status = status;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
