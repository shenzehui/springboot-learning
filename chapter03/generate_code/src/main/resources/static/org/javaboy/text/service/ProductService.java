package org.javaboy.text.mapper;

import org.javaboy.text.model.Product;
import org.javaboy.text.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ProductService{

    @Autowired
    ProductMapper productMapper;

    public List<Product> getAllProducts(){
        return productMapper.getAllProducts();
    }
}