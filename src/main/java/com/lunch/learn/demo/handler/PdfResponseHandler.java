package com.lunch.learn.demo.handler;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public class PdfResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity handle(String fileName, byte[] pdfContent) {
        HttpHeaders headers = getHttpHeaders(fileName);
        ByteArrayResource resource = getPdfResource(pdfContent);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    private ByteArrayResource getPdfResource(byte[] pdfContent) {
        ByteArrayInputStream iS = new ByteArrayInputStream(pdfContent);
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(IOUtils.toByteArray(iS));
            iS.close();

            return resource;
        } catch(Exception ex) {
            logger.warn("Failed to create byte array resource for pdf {}", ex.getMessage());
        }

        return resource;
    }

    private HttpHeaders getHttpHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        headers.add("content-disposition", "inline;filename='" + fileName + ".pdf'");
        return headers;
    }
}