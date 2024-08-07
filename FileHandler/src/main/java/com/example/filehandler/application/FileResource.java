package com.example.filehandler.application;

import com.example.filehandler.domain.api.FileHandlerApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
@Tag(name = "Files")
public class FileResource {

    private final FileHandlerApi fileHandlerApi;

    @GetMapping(value = "/images/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImageById(@PathVariable("imageId") UUID imageId){
        return ResponseEntity.ok(fileHandlerApi.getImageById(imageId));
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UUID> uploadImage(@RequestBody MultipartFile image){
        return ResponseEntity.ok(fileHandlerApi.uploadImage(image));
    }

}
