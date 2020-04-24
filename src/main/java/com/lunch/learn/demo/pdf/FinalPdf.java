package com.lunch.learn.demo.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.Document;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class FinalPdf implements DemoPdf {

    @Override
    public byte[] generatePdf() throws IOException {
        PdfReader reader = new PdfReader(new ByteArrayInputStream(getExamplePdf()));

        PdfDocument document = new PdfDocument(reader);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Document newDocument = initDoc(os);

        document.copyPagesTo(1, document.getNumberOfPages(), newDocument.getPdfDocument());

        newDocument.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(os.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    private byte[] getExamplePdf() throws IOException {
        URL pdf = ClassLoader.getSystemResource("static/CRMFB_1.2.1.pdf");
        return IOUtils.toByteArray(pdf);
    }
}
