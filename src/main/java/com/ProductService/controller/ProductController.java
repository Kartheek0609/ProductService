package com.ProductService.controller;

import com.ProductService.dtos.ProductRequestDto;
import com.ProductService.models.Category;
import com.ProductService.models.Product;
import com.ProductService.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {
        return new ArrayList<>();
    }


    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto requestDto){
        return new Product();

    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }
}
