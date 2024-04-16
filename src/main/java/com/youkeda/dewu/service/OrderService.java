package com.youkeda.dewu.service;

import com.youkeda.dewu.model.Order;

public interface OrderService {

    /**
     * 下单
     *
     * @param order    接收的Order模型
     * @return
     */
    public Order add(Order order);

}