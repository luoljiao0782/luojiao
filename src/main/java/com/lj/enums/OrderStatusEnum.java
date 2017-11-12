package com.lj.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by lj0782 on 2017/11/2.
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINLISH(1, "已完成"),
    CANCLE(2, "已取消");

    private Integer code;
    private String name;

    OrderStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
