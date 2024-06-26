package com.ProductService.service;

import com.ProductService.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    Product getSingleProduct(long id);
    List<Product> getAllProducts();
}
