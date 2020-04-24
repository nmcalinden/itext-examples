package com.lunch.learn.demo.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TablePdf implements DemoPdf {

    private static final String[] HEADERS = { "Column A", "Column B", "Column C", "Column D"};

    @Override
    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document doc = initDoc(outputStream);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

        addHeaders(font, table);
        addContent(font, table);

        doc.add(table);
        doc.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(outputStream.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    private void addHeaders(PdfFont font, Table table) {
        for(int i = 0; i < 4; i++) {
            Cell cell = new Cell().add(new Paragraph(HEADERS[i])
                    .setFont(font)
                    .setFontColor(ColorConstants.BLACK));
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);
        }
    }

    private void addContent(PdfFont font, Table table) {
        for (int aw = 0; aw < 16; aw++) {
            Cell cell = new Cell().add(new Paragraph("Demo")
                    .setFont(font)
                    .setFontColor(ColorConstants.BLACK));
            cell.setTextAlignment(TextAlignment.LEFT);
            table.addCell(cell);
        }
    }
}
