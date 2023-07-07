package com.example.Product.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder(toBuilder = true)
public class ProductDto {

    private String productName;

    private String productDescription;

    private String productCategory;



}
