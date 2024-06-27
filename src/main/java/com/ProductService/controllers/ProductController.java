package com.ProductService.controllers;

import com.ProductService.dtos.ProductRequestDto;
import com.ProductService.dtos.ProductResponseSelf;
import com.ProductService.exceptions.ProductNotPresentException;
import com.ProductService.models.Category;
import com.ProductService.models.Product;
import com.ProductService.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProduct(@PathVariable("id") Long id) {
        Product product;
        try{
            product=productService.getSingleProduct(id);
        } catch (ProductNotPresentException e) {
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product not present");
            return new ResponseEntity<>(
                    productResponseSelf, HttpStatus.NOT_FOUND);
        }catch(ArithmeticException e){
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Something went wrong");
            return new ResponseEntity<>(
                    productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ProductResponseSelf(product,"Success"), HttpStatus.OK);
    }

    @GetMapping("/product/exception/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProductException(@PathVariable("id") Long id)
            throws ProductNotPresentException {

        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(new ProductResponseSelf(product, "Success"), HttpStatus.OK);
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
