package com.lj.controller;

import com.lj.VO.ProductInfoVO;
import com.lj.VO.ProductVO;
import com.lj.VO.ResultVO;
import com.lj.entity.ProductCategory;
import com.lj.entity.ProductInfo;
import com.lj.service.ProductCategoryService;
import com.lj.service.ProductInfoService;
import com.lj.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by lj0782 on 2017/11/1.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuynerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表
     */
    @GetMapping("/list")
    public ResultVO list() {
        //1.获取上架商品
        List<ProductInfo> productInfos = productInfoService.findUp();
        //2.获取类目(一次性获取商品对应的所有类目)
        List<Integer> categoryTypes = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(categoryTypes);
        //3.拼装VO
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfos(productInfoVOS);
            productVOS.add(productVO);
        }
        return ResultVOUtil.success(productVOS);
    }

}
