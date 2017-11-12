package com.lj.service;

import com.lj.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lj0782 on 2017/11/2.
 */
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 查询单个订单.
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完成订单
     */
    OrderDTO finlish(OrderDTO orderDTO);

}
