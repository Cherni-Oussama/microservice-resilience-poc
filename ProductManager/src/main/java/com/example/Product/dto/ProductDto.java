package com.example.Product.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductDto {

    private String productName;

    private String productDescription;

    private String productCategory;



}
