package com.lj.DTO;

import lombok.Data;

/**
 * 购物车
 * Created by lj0782 on 2017/11/3.
 */
@Data
public class CartDTO {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
