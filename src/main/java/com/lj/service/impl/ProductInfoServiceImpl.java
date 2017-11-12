package com.lj.service.impl;

import com.lj.DTO.CartDTO;
import com.lj.entity.ProductInfo;
import com.lj.enums.ProductStatusEnum;
import com.lj.enums.ResultEnum;
import com.lj.exception.SellException;
import com.lj.repository.ProductInfoRepository;
import com.lj.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lj0782 on 2017/11/1.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUp() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void addStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer reslust = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(reslust);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void deStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {

            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer reslust = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (reslust > 0) {
                productInfo.setProductStock(reslust);
                repository.save(productInfo);
            } else {
                throw new SellException(ResultEnum.PRODUCTSTOCK_ERR);
            }
        }
    }
}
