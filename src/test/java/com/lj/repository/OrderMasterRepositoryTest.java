package com.lj.repository;

import com.lj.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lj0782 on 2017/11/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567890");
        orderMaster.setBuyerPhone("138803901524");
        orderMaster.setBuyerName("罗蛟");
        orderMaster.setBuyerAddress("成都");
        orderMaster.setBuyerOpenid("138803901524openid");
        orderMaster.setOrderAmount(new BigDecimal(99.99));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid("138803901524openid",pageRequest);
        Assert.assertNotEquals(0,orderMasterPage.getContent().size());
    }

}