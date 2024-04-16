package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.OrderDAO;
import com.youkeda.dewu.dataobject.OrderDO;
import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.OrderStatus;
import com.youkeda.dewu.model.ProductDetail;
import com.youkeda.dewu.service.OrderService;
import com.youkeda.dewu.service.ProductDetailService;
import com.youkeda.dewu.util.UUIDUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ProductDetailService productDetailService;

    @Override
    public Order add(Order order) {

        if (order == null) {
            return null;
        }
        //根据商品Id查询商品信息
        ProductDetail productDetail = productDetailService.get(order.getProductDetailId());
        if (productDetail == null) {
            return null;
        }
        order.setTotalPrice(productDetail.getPrice());
        order.setId(UUIDUtils.getUUID());
        order.setStatus(OrderStatus.WAIT_BUYER_PAY);
        //生成唯一订单号
        order.setOrderNumber(createOrderNumber());
        OrderDO orderDO = new OrderDO(order);
        int insert = orderDAO.insert(orderDO);
        if (insert == 1) {
            return order;
        }
        return null;
    }
    /**
     * 生成唯一订单号
     *
     * @return String
     */
    private String createOrderNumber() {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String date = LocalDateTime.now().format(dtf2);
        RAtomicLong atomicLong = redissonClient.getAtomicLong("myOrderPartNum" + date);
        atomicLong.expire(10, TimeUnit.SECONDS);
        long count = atomicLong.incrementAndGet();
        String orderId = date + "" + count;
        return orderId;
    }


}
