package com.example.filehandler.domain.spi;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileHandlerSpi {

    UUID uploadImage(MultipartFile file);

    Resource getImageById(UUID imageId);

}
