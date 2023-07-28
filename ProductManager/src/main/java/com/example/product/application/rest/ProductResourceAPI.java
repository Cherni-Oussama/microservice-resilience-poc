package com.example.product.application.rest;

import com.example.product.application.rest.model.request.RestProductRequest;
import com.example.product.application.rest.model.response.RestProductResponse;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface ProductResourceAPI {

  /**
   * Creates a new product based on the provided ProductDto.
   *
   * @param productDto The ProductDto containing the information for the new product.
   * @return A ResponseEntity containing the newly created Product object.
   */
  @PostMapping()
  ResponseEntity<RestProductResponse> createProduct(
      @RequestBody @Valid RestProductRequest productDto);

  /**
   * Updates the image of a product with the specified product ID.
   *
   * @param productId The ID of the product to update the image for.
   * @param image     The new image file for the product.
   * @return A ResponseEntity containing the updated Product object.
   */
  @PostMapping(value = "{productId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<RestProductResponse> updateProductImage(@PathVariable("productId") UUID productId,
      @RequestBody MultipartFile image);

}
