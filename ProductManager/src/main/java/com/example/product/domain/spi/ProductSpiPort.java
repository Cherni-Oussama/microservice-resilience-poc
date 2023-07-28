package com.example.product.domain.spi;

import com.example.product.domain.model.Product;
import com.example.product.infrastructure.entity.ProductEntity;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface ProductSpiPort {

  Product createNewProduct(Product productEntityDto);

  Product updateProductImage(UUID productId, MultipartFile image);

}
