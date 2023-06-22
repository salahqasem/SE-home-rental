package com.se.rental.service.impl;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.se.rental.service.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {

    AmazonS3 s3;

    public S3ServiceImpl() {
        System.setProperty("aws.accessKeyId", "AKIAUV37P6D6C7XIZX6B");
        System.setProperty("aws.secretKey", "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");

        AmazonS3ClientBuilder builder = AmazonS3Client.builder()
                .withRegion("us-west-2")
                .withCredentials(new SystemPropertiesCredentialsProvider());
        s3 = builder.build();

    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getBytes().length);
        metadata.setContentType("image/jpg");
        String fileName = UUID.randomUUID().toString() + ".jpg";
        s3.putObject(new PutObjectRequest("waa2023", fileName, new ByteArrayInputStream(file.getBytes()), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return "https://waa2023.s3.us-west-2.amazonaws.com/" + fileName;
    }
}
