package com.hjy.yyyproducer.mapper;

import entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insert(Order order);


}
