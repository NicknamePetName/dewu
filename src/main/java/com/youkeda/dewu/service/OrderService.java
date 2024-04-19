package com.youkeda.dewu.service;

import com.youkeda.dewu.dataobject.OrderDO;
import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.OrderStatus;
import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.param.QueryOrderParam;

public interface OrderService {
    /**
     * 下单
     *
     * @param order 接收的Order模型
     * @return
     */
    public Order add(Order order);

    /**
     * 查询订单
     *
     * @param queryOrderParam 查询参数
     * @return
     */
    Paging<Order> queryRecentPaySuccess(QueryOrderParam queryOrderParam);

    /**
     * 根据订单号查询
     *
     * @param orderNumber
     * @return Order
     */
    Order getByOrderNumber(String orderNumber);

    /**
     * 更新订单状态
     *
     * @param orderNumber
     * @param orderStatus
     * @return
     */
    Order updateOrderStatus(String orderNumber, OrderStatus orderStatus);

    /**
     * 更新商品付款人数
     *
     * @param orderNumber
     * @return
     */
    Order updateProductPersonNumber(String orderNumber);
}
