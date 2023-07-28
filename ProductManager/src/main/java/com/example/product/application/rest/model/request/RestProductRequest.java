package com.example.product.application.rest.model.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestProductRequest {

  private String productName;

  private String productDescription;

  private String productCategory;

}
