package com.example.Resilience.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobStorageException;
import com.example.Resilience.exception.FileCouldNotBeSavedToBlobException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AzureBlobService {

    private final BlobContainerClient containerClient;

    @Autowired
    public AzureBlobService(BlobServiceClient blobServiceClient) {
        String containerName = "images-container";
        this.createContainerIfNotExists(blobServiceClient, containerName);
        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public UUID uploadImage(MultipartFile file){
        var fileType = file.getContentType();
        if(!Objects.equals(fileType,"image/png")){
            throw new FileCouldNotBeSavedToBlobException("File is not of valid image type", null, null, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        if( file.getSize() > 500000L) {
            throw new FileCouldNotBeSavedToBlobException("Timeout while uploading image", null, null, HttpStatus.GATEWAY_TIMEOUT);
        }
        var blobId = UUID.randomUUID();
        log.info("Request to upload new Image to blob storage with Id {}", blobId);
        BlobClient blobClient = containerClient.getBlobClient(String.valueOf(blobId));
        BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
        blobHttpHeaders.setContentType(fileType);

        try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            blobClient.uploadWithResponse(inputStream, file.getSize(), null, blobHttpHeaders, null, null, null, null, null );
        }
        catch (IOException ce) {
            log.error("Error While Opening File: " + ce.getMessage());
            throw new FileCouldNotBeSavedToBlobException("Error uploading file to blob storage", null, null, HttpStatus.BAD_REQUEST);
        }
        return blobId;
    }

    public Resource getImageFromBlob(UUID imageId) {
        log.info("Request to retrieve existing image from blob storage");
        BlobClient blobClient = containerClient.getBlobClient(String.valueOf(imageId));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.downloadWithResponse(outputStream, null, null,null,false,null, null);
        return new ByteArrayResource(outputStream.toByteArray());
    }


    private void createContainerIfNotExists(BlobServiceClient blobServiceClient ,String containerName){
        try{
            log.info("Creating blob container with name {}", containerName);
            blobServiceClient.createBlobContainer(containerName);
        } catch (BlobStorageException e){
            log.info("Container {} Already exists", containerName);
        }
    }

}
