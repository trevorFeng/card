package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一句话描述该类作用:【好友管理】
 *
 * @author: trevor
 * @create: 2019-03-09 14:08
 **/
@Data
@NoArgsConstructor
public class FriendsManage {
    private Integer id;

    /**
     * 玩家id
     */
    private Integer userId;

    /**
     * 管理的好友id
     */
    private Integer manageFriendId;

    /**
     * 1未通过 ，0为未通过
     */
    private Byte allowFlag;

    public FriendsManage(Integer id, Integer userId, Integer manageFriendId, Byte allowFlag) {
        this.id = id;
        this.userId = userId;
        this.manageFriendId = manageFriendId;
        this.allowFlag = allowFlag;
    }
}