package com.lunch.learn.demo.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.Document;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BigPdf implements DemoPdf{

    @Override
    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Document document = initDoc(os);

        List<byte[]> pdfs = getPdfs();

        for(int i = 0; i < 100; i++) {
            for (byte[] pdf : pdfs) {
                PdfReader reader = new PdfReader(new ByteArrayInputStream(pdf));
                PdfDocument existingPdf = new PdfDocument(reader);
                existingPdf.copyPagesTo(1, existingPdf.getNumberOfPages(), document.getPdfDocument());
            }
        }

        document.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(os.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    private List<byte[]> getPdfs() throws IOException {
        byte[] basicPdf = new BasicPdf().generatePdf();
        byte[] tablePdf = new TablePdf().generatePdf();
        byte[] htmlPdf = new HTMLPdf().generatePdf();
        byte[] imagePdf = new ImagePdf().generatePdf();

        return Arrays.asList(basicPdf, tablePdf, htmlPdf, imagePdf);
    }
}
