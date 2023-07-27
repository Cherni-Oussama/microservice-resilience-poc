package com.example.product.infrastructure.adapter;

import com.example.product.domain.exception.ResourceNotFoundException;
import com.example.product.domain.model.Product;
import com.example.product.domain.spi.ProductSpiPort;
import com.example.product.infrastructure.mapper.ProductEntityMapper;
import com.example.product.infrastructure.repository.ProductRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductAdapter implements ProductSpiPort {

  private final ProductRepository productRepository;

  private final FileHandlerService fileHandlerService;

  private final ProductEntityMapper entityMapper;

  @Override
  public Product createNewProduct(Product product) {
    return entityMapper.mapToDomain(productRepository.save(entityMapper.mapToEntity(product)));
  }

  @Override
  public Product updateProductImage(UUID productId, MultipartFile image) {
    log.info("Request to update Product image");
    var product = productRepository.findById(productId)
        .orElseThrow(
            () -> new ResourceNotFoundException("Product Not Found", HttpStatus.NOT_FOUND));
    product.setProductImageId(fileHandlerService.uploadImageToBlob(image));
    return entityMapper.mapToDomain(product);
  }
}
