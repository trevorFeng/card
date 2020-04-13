package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一句话描述该类作用:【个人房卡信息】
 *
 * @author: trevor
 * @create: 2019-03-05 0:20
 **/
@Data
@NoArgsConstructor
public class PersonalCard {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 拥有的房卡数量
     */
    private Integer roomCardNum;

    public PersonalCard(Integer id, Integer userId, Integer roomCardNum) {
        this.id = id;
        this.userId = userId;
        this.roomCardNum = roomCardNum;
    }
}