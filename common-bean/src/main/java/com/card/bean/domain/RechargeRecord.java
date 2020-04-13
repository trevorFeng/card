package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RechargeRecord {
    private Integer id;

    private Integer userId;

    /**
     * 充值房卡数量
     */
    private Integer rechargeCard;

    /**
     * 房卡单价
     */
    private BigDecimal unitPrice;

    /**
     * 本次充值的总价
     */
    private BigDecimal totalPrice;

    /**
     * 充值时间
     */
    private Long time;

    public RechargeRecord(Integer id, Integer userId, Integer rechargeCard, BigDecimal unitPrice, BigDecimal totalPrice, Long time) {
        this.id = id;
        this.userId = userId;
        this.rechargeCard = rechargeCard;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.time = time;
    }
}