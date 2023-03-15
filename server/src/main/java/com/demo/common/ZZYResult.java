package com.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @desc
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ZZYResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ZZYResult<T> getInstance(int code , String message , T data) {
        return new ZZYResult<T>(code, message, data);
    }
}
