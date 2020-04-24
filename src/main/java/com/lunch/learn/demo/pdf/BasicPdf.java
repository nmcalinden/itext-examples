package com.lunch.learn.demo.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BasicPdf implements DemoPdf {

    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Document document = initDoc(os);

        PdfFont helveticaFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont helveticaBoldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Paragraph p = new Paragraph();

        String s = "The Gospel According to Bopper\n\nTwo households, both alike in dignity,\nIn fair Verona, where we lay our scene,\n" +
                "From ancient grudge break to new mutiny,\nWhere civil blood makes civil hands unclean.\n" +
                "From forth the fatal loins of these two foes\nA pair of star-cross'd lovers take their life;\n" +
                "Whole misadventured piteous overthrows\nDo with their death bury their parents' strife.\n" +
                "The fearful passage of their death-mark'd love,\nAnd the continuance of their parents' rage,\n" +
                "Which, but their children's end, nought could remove,\n Is now the two hours' traffic of our stage;\n" +
                "The which if you with patient ears attend,\nWhat here shall miss, our toil shall strive to mend.";

        for (int i = 0; i < s.length(); i++) {
            p.add(returnCorrectColor(s.charAt(i), helveticaFont, helveticaBoldFont));
        }

        document.add(p);
        document.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(os.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    private static Text returnCorrectColor(char letter, PdfFont helveticaFont, PdfFont helveticaBoldFont) {
        if (letter == 'b') {
            return new Text("b")
                    .setFontColor(ColorConstants.BLUE)
                    .setFontSize(16.0f)
                    .setFont(helveticaBoldFont);
        } else if (letter == 's') {
            return new Text("s")
                    .setFontColor(ColorConstants.GREEN)
                    .setFont(helveticaFont)
                    .setItalic();
        } else {
            return new Text(String.valueOf(letter))
                    .setFontColor(ColorConstants.BLACK)
                    .setFont(helveticaFont);
        }
    }
}
