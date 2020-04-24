package com.lunch.learn.demo.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class ImagePdf implements DemoPdf {

    @Override
    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream imageOs = new ByteArrayOutputStream();
        Document document = initDoc(imageOs);

        for(int i = 0; i < 150; i++) {
            document.add(new AreaBreak());
        }

        document.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(imageOs.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    public Document initDoc(ByteArrayOutputStream os) {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(os));

        Image pdfImg = getImage();
        ImageEventHandler handler = new ImageEventHandler(pdfImg);
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, handler);

        return new Document(pdfDoc);
    }

    private Image getImage() {
        URL img = ClassLoader.getSystemResource("static/dreams.png");
        ImageData imageData = ImageDataFactory.create(img);
        return new Image(imageData);
    }
}

class ImageEventHandler implements IEventHandler {

    private Image img;

    ImageEventHandler(Image img) {
        this.img = img;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamAfter(),
                page.getResources(), pdfDoc);
        Rectangle area = page.getPageSize();

        img.setFixedPosition(12, 300);
        img.setHorizontalAlignment(HorizontalAlignment.CENTER);


        new Canvas(aboveCanvas, pdfDoc, area)
                .add(img);
    }
}
