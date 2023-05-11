package org.javaboy.text.mapper;

import org.javaboy.text.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author szh
 */
@Mapper
public interface ProductMapper {

    /**
     * 获取所有商品
     *
     * @return
     */
    List<Product> getAllProducts();
}