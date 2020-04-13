package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一句话描述该类作用:【玩家在一间房的对局情况】
 *
 * @author: trevor
 * @create: 2019-03-04 23:21
 **/
@Data
@NoArgsConstructor
public class PersonalConfrontation {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private Long endTime;

    /**
     * 积分情况
     */
    private Integer integralCondition;


    public PersonalConfrontation(Integer id, Integer userId, Integer roomId, Long endTime, Integer integralCondition) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.endTime = endTime;
        this.integralCondition = integralCondition;
    }
}