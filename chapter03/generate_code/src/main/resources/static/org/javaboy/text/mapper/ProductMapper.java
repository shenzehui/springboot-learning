package org.javaboy.text.mapper;

import org.javaboy.text.model.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper{
    List<Product> getAllProducts();
}