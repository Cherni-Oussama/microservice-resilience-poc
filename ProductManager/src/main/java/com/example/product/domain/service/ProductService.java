package com.example.product.domain.service;

import com.example.product.domain.api.ProductApiPort;
import com.example.product.domain.model.Product;
import com.example.product.domain.spi.ProductSpiPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductApiPort {

  private final ProductSpiPort productSpiPort;

  public Product createNewProduct(Product productEntity) {
    return productSpiPort.createNewProduct(productEntity);
  }

  public Product updateProductImage(UUID productId, MultipartFile image) {
    return productSpiPort.updateProductImage(productId,image);
  }

  @Override
  public Product fetchById(UUID productId) {
    return productSpiPort.fetchById(productId);
  }

  @Override
  public void deleteById(UUID productId) {
    productSpiPort.deleteById(productId);
  }

  @Override
  public List<Product> fetchAll() {
    return productSpiPort.fetchAll();
  }

}
