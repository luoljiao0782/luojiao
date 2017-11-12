package com.lj.service.impl;

import com.lj.entity.ProductInfo;
import com.lj.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lj0782 on 2017/11/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123459");
        productInfo.setProductName("张飞牛肉");
        productInfo.setProductStock(300);
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductPrice(new BigDecimal(118.80));
        productInfo.setCategoryType(1);
        ProductInfo result = service.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        ProductInfo result = service.findOne("123456");
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = service.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getContent());
    }

    @Test
    public void findUp() throws Exception {
        List<ProductInfo> productInfos = service.findUp();
        Assert.assertNotEquals(0, productInfos.size());
    }

}