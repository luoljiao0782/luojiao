package com.lj.enums;

import lombok.Getter;

/**
 * Created by lj0782 on 2017/11/2.
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(0,"商品不存在"),
    PRODUCTSTOCK_ERR(1,"库存不足"),
    ORDERSTATUS_ERR(2,"订单状态不正确");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
