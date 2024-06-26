package com.ProductService.repositories;

import com.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByDescription(String description);
    Product findByName(String name);
    Product findByPrice(Float price);
}
