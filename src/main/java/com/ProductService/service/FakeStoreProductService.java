package com.ProductService.service;


import com.ProductService.dtos.ProductResponseDto;
import com.ProductService.models.Category;
import com.ProductService.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements  IProductService{
    public Product getSingleProduct(long id){
        RestTemplate restTemplate = new RestTemplate();
        ProductResponseDto response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);
        return getProductFromResponseDto(response);
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
