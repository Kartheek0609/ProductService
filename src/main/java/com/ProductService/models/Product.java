package com.ProductService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
@JsonSerialize
public class Product extends BaseModel implements Serializable {
    private String description;
    private String image;
    private float price;

    @ManyToOne
    private Category category;
}
