package com.lj.service.impl;

import com.lj.DTO.CartDTO;
import com.lj.DTO.OrderDTO;
import com.lj.converter.OrderMaster2OrderDTOConverter;
import com.lj.entity.OrderDetail;
import com.lj.entity.OrderMaster;
import com.lj.entity.ProductInfo;
import com.lj.enums.OrderStatusEnum;
import com.lj.enums.PayStatusEnum;
import com.lj.enums.ResultEnum;
import com.lj.exception.SellException;
import com.lj.repository.OrderDetailRepository;
import com.lj.repository.OrderMasterRepository;
import com.lj.service.OrderService;
import com.lj.service.ProductInfoService;
import com.lj.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lj0782 on 2017/11/2.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUUID();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1、查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3、写入数据库（包括orderMaster和orderDetail）
            //orderDetail写入
            orderDetail.setDetailId(KeyUtil.getUUID());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //orderMaster写入
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAY.getCode());
        orderMasterRepository.save(orderMaster);
        //4扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.deStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        BeanUtils.copyProperties(orderMaster, orderDTO);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
//        1、判断订单状态
        if(orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw  new SellException(ResultEnum.ORDERSTATUS_ERR);
        }
//        2、修改订单状态
        OrderMaster orderMaster =new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);
//        3、返回库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.addStock(cartDTOList);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finlish(OrderDTO orderDTO) {
//        1、判断订单状态
        if(orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw  new SellException(ResultEnum.ORDERSTATUS_ERR);
        }
//        2、修改订单状态
        OrderMaster orderMaster =new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINLISH.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
//        3、修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.PAIED.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
