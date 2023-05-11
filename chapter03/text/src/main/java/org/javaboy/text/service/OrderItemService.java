package org.javaboy.text.service;

import org.javaboy.text.model.OrderItem;
import org.javaboy.text.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author szh
 */
@Service
public class OrderItemService{

    @Autowired
    OrderItemMapper orderItemMapper;

    public List<OrderItem> getAllOrderItems(){
        return orderItemMapper.getAllOrderItems();
    }
}