package com.lunch.learn.demo.service;

import com.lunch.learn.demo.handler.PdfResponseHandler;
import com.lunch.learn.demo.pdf.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfService {

    private PdfResponseHandler responseHandler = new PdfResponseHandler();

    public ResponseEntity getBasicPdf() throws IOException {
        BasicPdf basicPdf = new BasicPdf();
        byte[] pdf =  basicPdf.generatePdf();

        return responseHandler.handle("basic", pdf);
    }

    public ResponseEntity getTablePdf() throws IOException {
        TablePdf tablePdf = new TablePdf();
        byte[] pdf =  tablePdf.generatePdf();

        return responseHandler.handle("table", pdf);
    }

    public ResponseEntity getImagePdf() throws IOException {
        ImagePdf imagePdf = new ImagePdf();
        byte[] pdf =  imagePdf.generatePdf();

        return responseHandler.handle("images", pdf);
    }

    public ResponseEntity getHtmlPdf() throws IOException {
        HTMLPdf htmlPdf = new HTMLPdf();
        byte[] pdf =  htmlPdf.generatePdf();

        return responseHandler.handle("html", pdf);
    }

    public ResponseEntity getMergePdf() throws IOException {
        MergePdf mergePdf = new MergePdf();
        byte[] pdf =  mergePdf.generatePdf();

        return responseHandler.handle("merge", pdf);
    }

    public ResponseEntity getBigPdf() throws IOException {
        BigPdf bigPdf = new BigPdf();
        byte[] pdf =  bigPdf.generatePdf();

        return responseHandler.handle("big", pdf);
    }

    public ResponseEntity getFinalPdf() throws IOException {
        FinalPdf finalPdf = new FinalPdf();
        byte[] pdf =  finalPdf.generatePdf();

        return responseHandler.handle("final", pdf);
    }
}
