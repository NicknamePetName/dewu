package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.OrderDAO;
import com.youkeda.dewu.dataobject.OrderDO;
import com.youkeda.dewu.model.*;
import com.youkeda.dewu.param.QueryOrderParam;
import com.youkeda.dewu.service.OrderService;
import com.youkeda.dewu.service.ProductDetailService;
import com.youkeda.dewu.service.UserService;
import com.youkeda.dewu.util.UUIDUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private UserService userService;

    @Override
    public Order add(Order order) {

        if (order == null) {
            return null;
        }
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

    @Override
    public Paging<Order> queryRecentPaySuccess(QueryOrderParam queryOrderParam) {
        Paging<Order> result = new Paging<>();

        if (queryOrderParam == null) {
            queryOrderParam = new QueryOrderParam();
        }

        int counts = orderDAO.selectCounts(queryOrderParam);
        if (counts < 1) {
            result.setTotalCount(0);
            return result;
        }
        List<OrderDO> orderDOS = orderDAO.pageQuery(queryOrderParam);
        List<Order> orders = new ArrayList<>();
        List<String> productDetailIds = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();

        orderDOS.forEach(orderDO -> {
            orders.add(orderDO.convertToModel());
            productDetailIds.add(orderDO.getProductDetailId());
            userIds.add(Long.parseLong(orderDO.getUserId()));
        });

        List<User> users = userService.queryUser(userIds);
        List<ProductDetail> productDetails = productDetailService.queryProductDetail(productDetailIds);
        Map<String, ProductDetail> productDetailMap = productDetails.stream().collect(
                Collectors.toMap(ProductDetail::getId, t -> t));
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, t -> t));

        orders.forEach(order -> {
            order.setProductDetail(productDetailMap.get(order.getProductDetailId()));
            order.setUser(userMap.get(Long.parseLong(order.getUserId())));
        });
        result.setData(orders);
        result.setTotalCount(counts);
        result.setPageNum(orders.size());
        result.setPageSize(queryOrderParam.getPageSize());
        result.setTotalPage(orders.size() / queryOrderParam.getPageNum());
        return result;
    }

    /**
     * 生成唯一订单号
     *
     * @return String
     */
    private String createOrderNumber() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String date = dtf2.format(localDateTime);
        RAtomicLong aLong = redisson.getAtomicLong("myOrderPartNum" + date);
        aLong.expire(10, TimeUnit.SECONDS);
        long count = aLong.incrementAndGet();
        String orderId = date + count;
        return orderId;
    }
}
