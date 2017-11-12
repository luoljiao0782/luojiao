package com.lj.service;

import com.lj.DTO.CartDTO;
import com.lj.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息
 * Created by lj0782 on 2017/11/1.
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有商品
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询所有上架商品
     */
    List<ProductInfo> findUp();

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void addStock(List<CartDTO> cartDTOList);

    //减库存
    void deStock(List<CartDTO> cartDTOList);
}
