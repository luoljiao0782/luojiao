package com.lj.repository;

import com.lj.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lj0782 on 2017/11/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    @Autowired
    private ProductCategoryRepository repository;

    @Test
//    @Transactional//不提交数据到数据库
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(2);
        ProductCategory reslut = repository.save(productCategory);
        Assert.assertNotNull(reslut);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> reslut = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,reslut.size());
    }
}