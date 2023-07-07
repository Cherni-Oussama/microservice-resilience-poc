package com.example.Product.service.impl;

import lombok.extern.slf4j.Slf4j;
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

    public UUID uploadImageToBlob(MultipartFile image) {
        String url = "http://localhost:1234/images";

        // Set the headers and content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the request body with the image file
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("image", image); // Replace with the actual file path

        // Create the HTTP entity with headers and request body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the HTTP POST request
        ResponseEntity<UUID> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UUID.class);

        return responseEntity.getBody();
    }

}
