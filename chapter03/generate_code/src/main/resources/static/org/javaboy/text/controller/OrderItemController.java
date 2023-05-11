package org.javaboy.text.controller;

import org.javaboy.text.model.OrderItem;
import org.javaboy.text.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class OrderItemController{

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orderitems")
    public List<OrderItem> getAllOrderItem(){
        return orderItemService.getAllOrderItems();
    }
}