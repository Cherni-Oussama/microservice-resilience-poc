package com.example.Product.controller;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;
import com.example.Product.service.ProductService;
import com.example.Product.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createNewProduct(productDto));
    }

}
