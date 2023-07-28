package com.example.product.domain.api;

import com.example.product.domain.model.Product;
import com.example.product.infrastructure.entity.ProductEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface ProductApiPort {

  Product createNewProduct(Product productEntityDto);

  Product updateProductImage(UUID productId, MultipartFile image);

  Product fetchById(UUID productId);

  void deleteById(UUID productId);

  List<Product> fetchAll();

}
