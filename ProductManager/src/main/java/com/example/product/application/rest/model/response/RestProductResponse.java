package com.example.product.application.rest.model.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestProductResponse {

  private UUID productId;

  private String productName;

  private String productDescription;

  private String productCategory;

}
