package com.card.bean.bo;


/**
 * @author trevor
 * @date 2019/3/7 13:33
 */
public class ResponseHelper {

    public static <T> JsonEntity<T> of(T object) {
        JsonEntity<T> response = new JsonEntity<>();
        response.setData(object);
        return response;
    }

    public static <T> JsonEntity<T> ofNothing() {
        return of(null);
    }

    public static <T> JsonEntity<T> withErrorMessage(String message) {
        return ofNothing().setStatus(500).setMessage(message);
    }

}

