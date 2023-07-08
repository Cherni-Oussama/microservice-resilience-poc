package com.example.Product.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Slf4j
public class BlobService {

    private final RestTemplate restTemplate;

    public BlobService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "backendImages")
    public UUID uploadImageToBlob(MultipartFile image) {
        String url = "http://localhost:1234/images";

        Resource imageResource = image.getResource();

        // Set the headers and content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the request body with the image file
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("image", imageResource); // Replace with the actual file path

        // Create the HTTP entity with headers and request body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the HTTP POST request
        ResponseEntity<UUID> responseEntity = restTemplate.postForEntity(url, requestEntity, UUID.class);
        return responseEntity.getBody();
    }

}
