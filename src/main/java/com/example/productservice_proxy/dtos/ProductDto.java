package com.example.productservice_proxy.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ProductDto {
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;

}
