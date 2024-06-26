package com.ProductService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product extends BaseModel {
    private String descr;
    private String image;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;
}
