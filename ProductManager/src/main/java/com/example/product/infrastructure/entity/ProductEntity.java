package com.example.product.infrastructure.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

  @Id
  private UUID productId;

  private String productName;

  private String productDescription;

  private UUID productImageId;

  private String productCategory;

  private LocalDateTime createdAt;

  private LocalDateTime modifiedAt;

}
