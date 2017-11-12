package com.lj.service;

import com.lj.entity.ProductCategory;

import java.util.List;

/**
 * Created by lj0782 on 2017/11/1.
 */
public interface ProductCategoryService {
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory findOne(Integer id);
}
