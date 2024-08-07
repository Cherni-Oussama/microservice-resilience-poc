package com.example.filehandler.domain.service;

import com.example.filehandler.domain.api.FileHandlerApi;
import com.example.filehandler.domain.spi.FileHandlerSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileHandlerService implements FileHandlerApi {

    private final FileHandlerSpi fileHandlerSpi;

    @Override
    public UUID uploadImage(MultipartFile file) {
        return fileHandlerSpi.uploadImage(file);
    }

    @Override
    public Resource getImageById(UUID imageId) {
        return fileHandlerSpi.getImageById(imageId);
    }

}
