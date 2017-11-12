package com.lj.VO;

import lombok.Data;

/**
 * http请求返回结果
 * Created by lj0782 on 2017/11/2.
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    private T data;
}
