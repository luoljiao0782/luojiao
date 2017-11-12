package com.lj.service.impl;

import com.lj.DTO.OrderDTO;
import com.lj.entity.OrderDetail;
import com.lj.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lj0782 on 2017/11/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void createOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("罗蛟");
        orderDTO.setBuyerPhone("138803901524");
        orderDTO.setBuyerAddress("成都");
        orderDTO.setBuyerOpenid("138803901524openid");

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(2);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123458");
        orderDetail2.setProductQuantity(3);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("创建订单=====>>>>>{}", result);
    }

    @Test
    public void findOne() throws Exception{

        OrderDTO orderDTO = orderService.findOne("4bf939e2821b46288d9a63bf85f99bc2");
        log.info("orderDTO===>>>>{}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception{
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList("138803901524openid", pageRequest);
        log.error("orderDTOPage===>>>>{}",orderDTOPage);
        Assert.assertNotEquals(0,orderDTOPage.getContent().size());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("4bf939e2821b46288d9a63bf85f99bc2");

        OrderDTO result = orderService.cancel(orderDTO);
        log.info("orderDTO===>>>>{}",result);
        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(),result.getOrderStatus());

    }

    @Test
    public void finlish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("f502d117851541b7ab95dc154abec479");
        OrderDTO result = orderService.finlish(orderDTO);
        log.info("orderDTO===>>>>{}",result);
        Assert.assertEquals(OrderStatusEnum.FINLISH.getCode(),result.getOrderStatus());
    }

}