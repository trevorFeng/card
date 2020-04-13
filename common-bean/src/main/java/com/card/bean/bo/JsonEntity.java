package com.card.bean.bo;

import lombok.Data;

/**
 * @author trevor
 * @date 2019/3/4 11:02
 */
@Data
public class JsonEntity<T> {


    private Integer status;

    private String requestId;

    private String message;

    private T data;


    public JsonEntity() {
        this.status = 200;
    }

    public JsonEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public JsonEntity setMessage(String message) {
        this.message = message;
        return this;
    }

}
