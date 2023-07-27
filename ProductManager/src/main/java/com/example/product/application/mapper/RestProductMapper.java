package com.example.product.application.mapper;

import com.example.product.application.rest.model.request.RestProductRequest;
import com.example.product.application.rest.model.response.RestProductResponse;
import com.example.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestProductMapper {

  RestProductResponse mapToRest(Product product);

  Product mapToDomain(RestProductRequest request);

}
