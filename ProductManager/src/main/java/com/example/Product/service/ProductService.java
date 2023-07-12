package com.example.Product.service;

import com.example.Product.dto.ProductDto;
import com.example.Product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductService {

    Product createNewProduct(ProductDto productDto);

    Product updateProductImage(UUID productId, MultipartFile image);
}
