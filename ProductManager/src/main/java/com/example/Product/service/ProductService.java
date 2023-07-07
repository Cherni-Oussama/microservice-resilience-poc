package com.example.Product.service;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductService {

    Product createNewProduct(ProductDto productDto);

    Product updateProductImage(UUID productId, MultipartFile image);
}
