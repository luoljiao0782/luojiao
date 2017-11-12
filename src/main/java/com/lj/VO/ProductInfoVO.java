package com.lj.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情VO
 * Created by lj0782 on 2017/11/2.
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    //    商品名字
    @JsonProperty("name")
    private String productName;
    //    商品单价
    @JsonProperty("price")
    private BigDecimal productPrice;
    //    商品描述
    @JsonProperty("description")
    private String productDescription;
    //    商品图标地址
    @JsonProperty("icon")
    private String productIcon;
}
