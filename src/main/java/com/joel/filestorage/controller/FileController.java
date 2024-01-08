package com.joel.filestorage.controller;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final BlobContainerClient blobContainerClient;

    @Autowired
    public FileController(BlobContainerClient blobContainerClient) {
        this.blobContainerClient = blobContainerClient;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        BlobClient blobClient = this.blobContainerClient.getBlobClient(file.getOriginalFilename());
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        String url = blobClient.getBlobUrl();

        return "File uploaded successfully - URL: " + url;
    }
}
