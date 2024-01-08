package com.joel.filestorage.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServiceConfig {

    @Value("${spring.cloud.azure.storage.blob.sas-token}")
    private String sasToken;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String blobEndpoint;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Bean
    public BlobContainerClient createBlobContainerClient() {

        return new BlobContainerClientBuilder()
                .sasToken(sasToken)
                .containerName(containerName)
                .endpoint(blobEndpoint)
                .buildClient();

    }

}
