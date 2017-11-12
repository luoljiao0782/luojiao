package com.lj.exception;

import com.lj.enums.ResultEnum;

import javax.xml.transform.Result;

/**
 * Created by lj0782 on 2017/11/2.
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
