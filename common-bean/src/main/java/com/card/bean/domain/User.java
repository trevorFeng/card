package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Integer id;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 唯一的openid
     */
    private String openId;

    /**
     * hash值
     */
    private String hash;

    /**
     * 本表中自关联的userId，实则为同一用户（微信账号和闲聊账号）
     */
    private Integer relationUserId;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 用户昵称
     */
    private String appName;

    /**
     * 用户头像地址
     */
    private String appPictureUrl;

    /**
     * 0代表微信，1代表闲聊
     */
    private Byte type;

    /**
     * 是否开启好友管理，1为是，0为否
     */
    private Byte friendManageFlag;

    public User(Integer id, String realName, String idCard,
                String openId, String hash, Integer relationUserId,
                String phoneNumber, String appName, String appPictureUrl,
                Byte type, Byte friendManageFlag) {
        this.id = id;
        this.realName = realName;
        this.idCard = idCard;
        this.openId = openId;
        this.hash = hash;
        this.relationUserId = relationUserId;
        this.phoneNumber = phoneNumber;
        this.appName = appName;
        this.appPictureUrl = appPictureUrl;
        this.type = type;
        this.friendManageFlag = friendManageFlag;
    }
}