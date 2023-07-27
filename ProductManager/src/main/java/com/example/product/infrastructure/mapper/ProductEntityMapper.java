package com.example.product.infrastructure.mapper;

import com.example.product.domain.model.Product;
import com.example.product.infrastructure.entity.ProductEntity;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface ProductEntityMapper {

  @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
  ProductEntity mapToEntity(Product product);

  Product mapToDomain(ProductEntity productEntity);

}
