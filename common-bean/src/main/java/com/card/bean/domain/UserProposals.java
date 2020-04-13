package com.card.bean.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProposals {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 提议或异常信息
     */
    private String message;

    /**
     * 照片的url,json字符串
     */
    private String fileUrls;

    public UserProposals(Integer id, Integer userId, String message, String fileUrls) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.fileUrls = fileUrls;
    }
}