package com.lunch.learn.demo.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

interface DemoPdf {

    byte[] generatePdf() throws IOException;

    default Document initDoc(ByteArrayOutputStream pdfOs) {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfOs));
        return new Document(pdfDoc);
    }
}
