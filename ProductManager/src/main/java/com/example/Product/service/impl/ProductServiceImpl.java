package com.example.Product.service.impl;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;
import com.example.Product.repository.ProductRepository;
import com.example.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createNewProduct(ProductDto productDto) {
        return productRepository.save(Product.builder()
                        .productName(productDto.getProductName())
                        .productCategory(productDto.getProductCategory())
                        .productDescription(productDto.getProductDescription())
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .productImageId(null)
                .build());
    }
}
