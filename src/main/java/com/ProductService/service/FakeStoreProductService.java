package com.ProductService.service;


import com.ProductService.dtos.ProductResponseDto;
import com.ProductService.exceptions.ProductNotPresentException;
import com.ProductService.models.Category;
import com.ProductService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements  IProductService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public Product getSingleProduct(long id) throws ProductNotPresentException{
        /*
        if(id<=0 || id>20 && id<=40){
            throw new ProductNotPresentException();
        }
        if(id>40){
            throw new ArithmeticException();
        }
        ProductResponseDto response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);
        return getProductFromResponseDto(response);

         */
        if(redisTemplate.opsForHash().hasKey(id, "something")==false) {

            if (id > 20) {
                throw new ProductNotPresentException();
            }

            // I should pass this 'id' to fakestore and get the details of this product.
            // "https://fakestoreapi.com/products/1"
            ProductResponseDto response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                    ProductResponseDto.class);

            redisTemplate.opsForHash().put(id, "something", getProductFromResponseDto(response));
        }
        return (Product) redisTemplate.opsForHash().get(id, "something");

    }

    @Override
    public List<Product> getAllProducts(){
        ProductResponseDto[] products= restTemplate.getForObject("https://fakestoreapi.com/products/",
                ProductResponseDto[].class);
        List<Product> output = new ArrayList<>();
        for(ProductResponseDto productResponseDto: products){
            output.add(getProductFromResponseDto(productResponseDto));
        }

        return output;
    }
    private Product getProductFromResponseDto(ProductResponseDto response){
        Product product = new Product();
        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setDescription(response.getDescription());
        product.setPrice(response.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(response.getCategory());
        product.setImage(response.getImage());

        return product;
    }

}
