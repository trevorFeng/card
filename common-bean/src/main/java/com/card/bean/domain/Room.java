package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Room {

    /**
     * 主键id,房间编号,从10000开始
     */
    private Integer id;

    /**
     * 开房时间
     */
    private Long entryTime;

    /**
     * 开房人的id（用户id）
     */
    private Integer roomAuth;

    /**
     * 是否激活,0为未激活,1为激活，2为房间使用完成后关闭，3为房间未使用关闭
     */
    private Byte status;

    /**
     * 房间类型 1为13人牛牛，2为10人牛牛，3为6人牛牛 ，4为金花
     */
    private Byte roomType;

    /**
     * 进行到了多少句
     */
    private Integer runingNum;

    /**
     * 总局数
     */
    private Integer totalNum;

    public Room(Integer id, Long entryTime, Integer roomAuth, Byte status, Byte roomType, Integer runingNum, Integer totalNum) {
        this.id = id;
        this.entryTime = entryTime;
        this.roomAuth = roomAuth;
        this.status = status;
        this.roomType = roomType;
        this.runingNum = runingNum;
        this.totalNum = totalNum;
    }
}