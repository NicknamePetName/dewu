package com.youkeda.dewu.dao;

import com.youkeda.dewu.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDAO {
    int insert(OrderDO order);

}
