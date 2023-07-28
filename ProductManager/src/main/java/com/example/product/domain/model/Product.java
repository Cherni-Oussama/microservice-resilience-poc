package com.example.product.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

  private UUID productId;

  private String productName;

  private String productDescription;

  private UUID productImageId;

  private String productCategory;

  private LocalDateTime createdAt;

  private LocalDateTime modifiedAt;

}
