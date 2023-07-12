package com.example.Product.service.impl;

import com.example.Product.dto.ProductDto;
import com.example.Product.entity.Product;
import com.example.Product.exception.ResourceNotFoundException;
import com.example.Product.repository.ProductRepository;
import com.example.Product.service.ProductService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final BlobService blobService;

    @RateLimiter(name = "backendImages")
    @Override
    public Product createNewProduct(ProductDto productDto) {
        log.info("Request to create new product");
        return productRepository.save(Product.builder()
                        .productId(UUID.randomUUID())
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
        log.info("Request to update Product image");
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found", HttpStatus.NOT_FOUND));
        product.setProductImageId(blobService.uploadImageToBlob(image));
        return product;
    }
}
