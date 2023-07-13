package com.example.Product.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileHandlerService {

    @Value("${blob-service.uri}")
    private String blobServiceUri;

    private final RestTemplate restTemplate;

    /**
     * Uploads an image file to a Blob storage using FileHandler service.
     * Applies retry and circuit breaker policies for resilience.
     *
     * @param image The image file to upload.
     * @return The UUID identifier of the uploaded image.
     */
    @Retry(name = "fileHandlerRetry")
    @CircuitBreaker(name = "fileHandlerCB")
    public UUID uploadImageToBlob(MultipartFile image) {
        log.info("Requesting FileHandler to save image with type {}", image.getContentType());
        String url = String.format("http://%s/images",blobServiceUri);
        Resource imageResource = image.getResource();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("image", imageResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<UUID> responseEntity = restTemplate.postForEntity(url, requestEntity, UUID.class);
        return responseEntity.getBody();
    }

}
