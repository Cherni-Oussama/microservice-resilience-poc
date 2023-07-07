package com.example.Product.service.impl;

import com.example.Product.DTOs.ProductDto;
import com.example.Product.entities.Product;
import com.example.Product.exception.ResourceNotFoundException;
import com.example.Product.repository.ProductRepository;
import com.example.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final BlobService blobService;

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

    @Override
    public Product updateProductImage(UUID productId, MultipartFile image) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found", HttpStatus.NOT_FOUND));
        product.setProductImageId(blobService.uploadImageToBlob(image));
        return product;
    }
}
