package com.example.Product.controller;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;
import com.example.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createNewProduct(productDto));
    }

    @PostMapping(value = "{productId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProductImage( @PathVariable("productId") UUID productId,
            @RequestBody MultipartFile image){
        return ResponseEntity.ok(productService.updateProductImage(productId,image));
    }


}
