package com.SAlvesjr.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class S3Service {

	private Logger LOOGER = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3service;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePaht) {
		try {
			File file = new File(localFilePaht);
			s3service.putObject(bucketName, "teste.jpg", file);
			LOOGER.info("upload completo");
		} catch (AmazonServiceException e) {
			LOOGER.info("AmazonServiceException " + e.getErrorMessage());
			LOOGER.info("Status code " + e.getErrorCode());
		} catch (AmazonClientException e) {
			LOOGER.info("AmazonClientException " + e.getMessage());
		}

	}

}
