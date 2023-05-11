package org.javaboy.text.mapper;

import org.javaboy.text.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderItemMapper{
    List<OrderItem> getAllOrderItems();
}