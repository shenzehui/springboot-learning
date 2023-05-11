package org.javaboy.text.mapper;

import org.javaboy.text.model.OrderItem;
import org.javaboy.text.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class OrderItemService{

    @Autowired
    OrderItemMapper orderItemMapper;

    public List<OrderItem> getAllOrderItems(){
        return orderItemMapper.getAllOrderItems();
    }
}