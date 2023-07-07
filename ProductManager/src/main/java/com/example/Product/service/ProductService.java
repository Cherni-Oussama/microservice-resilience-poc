package com.example.Product.service;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;

public interface ProductService {

    Product createNewProduct(ProductDto productDto);
}
