package com.example.product.application.rest;

import com.example.product.application.mapper.RestProductMapper;
import com.example.product.application.rest.model.request.RestProductRequest;
import com.example.product.application.rest.model.response.RestProductResponse;
import com.example.product.domain.api.ProductApiPort;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
@RequiredArgsConstructor
public class ProductResource implements ProductResourceAPI {

  private final ProductApiPort productApiPort;

  private final RestProductMapper productMapper;


  /**
   * Creates a new product based on the provided ProductDto.
   *
   * @param productRequest The ProductDto containing the information for the new product.
   * @return A ResponseEntity containing the newly created Product object.
   */
  @RateLimiter(name = "productCreationRL")
  @PostMapping()
  public ResponseEntity<RestProductResponse> createProduct(
      @RequestBody @Valid RestProductRequest productRequest) {
    return ResponseEntity.ok(productMapper.mapToRest(
        productApiPort.createNewProduct(productMapper.mapToDomain(productRequest))));
  }

  /**
   * Updates the image of a product with the specified product ID.
   *
   * @param productId The ID of the product to update the image for.
   * @param image     The new image file for the product.
   * @return A ResponseEntity containing the updated Product object.
   */
  @PostMapping(value = "{productId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<RestProductResponse> updateProductImage(
      @PathVariable("productId") UUID productId, @RequestBody MultipartFile image) {
    return ResponseEntity.ok(
        productMapper.mapToRest(productApiPort.updateProductImage(productId, image)));
  }

  @Override
  public ResponseEntity<RestProductResponse> fetchById(UUID productId) {
    return ResponseEntity.ok().body(productMapper.mapToRest(productApiPort.fetchById(productId)));
  }

  @Override
  public ResponseEntity<List<RestProductResponse>> fetchAllProducts() {
    return ResponseEntity.ok(
        productApiPort.fetchAll()
            .stream()
            .map(productMapper::mapToRest)
            .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<Void> deleteProductById(UUID productId) {
    productApiPort.deleteById(productId);
    return ResponseEntity.noContent().build();
  }


}
