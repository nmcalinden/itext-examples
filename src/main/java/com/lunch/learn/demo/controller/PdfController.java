package com.lunch.learn.demo.controller;

import com.lunch.learn.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping(value = "/api/pdf/basic", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateBasicPdf() throws IOException {
       return pdfService.getBasicPdf();
    }

    @GetMapping(value = "/api/pdf/table", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateTablePdf() throws IOException {
        return pdfService.getTablePdf();
    }

    @GetMapping(value = "/api/pdf/images", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateImagePdf() throws IOException {
        return pdfService.getImagePdf();
    }

    @GetMapping(value = "/api/pdf/html", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateHtmlPdf() throws IOException {
        return pdfService.getHtmlPdf();
    }

    @GetMapping(value = "/api/pdf/merge", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateMergePdfs() throws IOException {
        return pdfService.getMergePdf();
    }

    @GetMapping(value = "/api/pdf/big", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateBigPdf() throws IOException {
        return pdfService.getBigPdf();
    }

    @GetMapping(value = "/api/pdf/final", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody ResponseEntity generateFinalPdf() throws IOException {
        return pdfService.getFinalPdf();
    }
}
