package com.lj.enums;

import lombok.Getter;

/**
 * 订单支付状态
 * Created by lj0782 on 2017/11/2.
 */
@Getter
public enum PayStatusEnum {
    UNPAY(0, "未支付"), PAIED(1, "已支付");
    private Integer code;
    private String name;

    PayStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
