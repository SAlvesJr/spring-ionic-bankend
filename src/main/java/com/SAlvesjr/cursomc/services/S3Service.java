package com.SAlvesjr.cursomc.services;

import java.io.InputStream;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SAlvesjr.cursomc.services.exception.FileException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOOGER = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3service;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (Exception e) {
			throw new FileException("Erro de IO " + e.getMessage());
		}

	}

	private URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			s3service.putObject(bucketName, fileName, is, metadata);
			LOOGER.info("upload completo");
			return s3service.getUrl(bucketName, fileName).toURI();
		} catch (Exception e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}

}
