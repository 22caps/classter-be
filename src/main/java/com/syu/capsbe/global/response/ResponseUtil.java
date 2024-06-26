package com.syu.capsbe.global.response;

import com.syu.capsbe.global.response.message.BasicResponse;
import com.syu.capsbe.global.response.message.ErrorResponse;

public class ResponseUtil {

    public static <T> BasicResponse<T> success(T response) {
        return new BasicResponse<>(true, response, null);
    }

    public static BasicResponse<?> error(ErrorResponse e) {
        return new BasicResponse<>(false, null, e);
    }
}
