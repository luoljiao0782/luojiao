package com.lj.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
//    商品名字
    private String productName;
//    商品单价
    private BigDecimal productPrice;
//    商品库存
    private Integer productStock;
//    商品描述
    private String productDescription;
//    商品图标地址
    private String productIcon;
//    商品状态 0正常 1下架
    private Integer productStatus;
//    类目编码
    private Integer categoryType;
}
