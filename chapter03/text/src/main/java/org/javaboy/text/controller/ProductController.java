package org.javaboy.text.controller;

import org.javaboy.text.model.Product;
import org.javaboy.text.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author szh
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }
}