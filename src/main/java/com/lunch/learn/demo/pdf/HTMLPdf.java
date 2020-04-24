package com.lunch.learn.demo.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class HTMLPdf implements DemoPdf {

    @Override
    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Document document = initDoc(os);

        String html = getHTMLExample();
        List<IElement> elements = HtmlConverter.convertToElements(html);
        for(IElement element : elements) {
            document.add((IBlockElement) element);
        }

        document.close();

        ByteArrayInputStream iS = new ByteArrayInputStream(os.toByteArray());
        return IOUtils.toByteArray(iS);
    }

    private String getHTMLExample() {
        return "<html>\n<head>\n<style>\ntable, td {\n  border: 1px solid black;\n" +
                "}\n</style>\n</head>\n<body>\n\n<h1 style=\"color:blue;\">This is a heading</h1>\n" +
                "<p style=\"color:red;\">This is a paragraph.</p>\n\n<h2>Bordered Table</h2>\n" +
                "\n<table style=\"width:100%\">\n  <tr>\n    <td>Firstname</td>\n    <td>Lastname</td> \n  <td>Age</td>\n" +
                "  </tr>\n  <tr>\n    <td>Jill</td>\n    <td>Smith</td>\n    <td>50</td>\n" +
                "  </tr>\n  <tr>\n    <td>Eve</td>\n    <td>Jackson</td>\n    <td>94</td>\n" +
                "  </tr>\n  <tr>\n    <td>John</td>\n    <td>Doe</td>\n    <td>80</td>\n" +
                "  </tr>\n</table>\n\n</body>\n</html>";
    }
}