package com.lj.DTO;

import com.lj.entity.OrderDetail;
import com.lj.enums.OrderStatusEnum;
import com.lj.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by lj0782 on 2017/11/2.
 */
@Data
public class OrderDTO {

    private String orderId;
    //买家姓名
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //邮寄地址
    private String buyerAddress;
    //买家openid
    private String buyerOpenid;
    //订单总价
    private BigDecimal orderAmount;
    //订单状态 默认0新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态 默认0未支付 1已支付
    private Integer payStatus = PayStatusEnum.UNPAY.getCode();

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
