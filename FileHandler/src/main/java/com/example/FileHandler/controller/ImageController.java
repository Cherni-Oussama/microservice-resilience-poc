package com.example.FileHandler.controller;

import com.example.FileHandler.service.AzureBlobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
@Tag(name = "Images CRUD")
public class ImageController {


    private final AzureBlobService azureBlobService;

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable("imageId") UUID imageId){
        return ResponseEntity.ok(azureBlobService.getImageFromBlob(imageId));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UUID> updateCourseCoverImage(@RequestBody MultipartFile image){
        return ResponseEntity.ok(azureBlobService.uploadImage(image));
    }

}
