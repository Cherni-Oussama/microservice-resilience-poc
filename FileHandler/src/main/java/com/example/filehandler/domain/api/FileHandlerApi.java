package com.example.filehandler.domain.api;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileHandlerApi {

    UUID uploadImage(MultipartFile file);

    Resource getImageById(UUID imageId);

}
