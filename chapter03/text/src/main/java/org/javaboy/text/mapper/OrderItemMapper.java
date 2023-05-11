package org.javaboy.text.mapper;

import org.javaboy.text.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author szh
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 获取所有订单
     *
     * @return
     */
    List<OrderItem> getAllOrderItems();
}