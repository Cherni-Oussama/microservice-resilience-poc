package com.example.Product.controller;

import com.example.Product.dto.ProductDto;
import com.example.Product.entity.Product;
import com.example.Product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    /**
     * Creates a new product based on the provided ProductDto.
     *
     * @param productDto The ProductDto containing the information for the new product.
     * @return A ResponseEntity containing the newly created Product object.
     */
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createNewProduct(productDto));
    }

    /**
     * Updates the image of a product with the specified product ID.
     *
     * @param productId The ID of the product to update the image for.
     * @param image The new image file for the product.
     * @return A ResponseEntity containing the updated Product object.
     */
    @PostMapping(value = "{productId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProductImage(
            @PathVariable("productId") UUID productId,
            @RequestBody MultipartFile image){
        return ResponseEntity.ok(productService.updateProductImage(productId,image));
    }




}
