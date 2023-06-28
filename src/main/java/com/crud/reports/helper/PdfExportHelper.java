package com.crud.reports.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

public class PdfExportHelper {

    /**
     * Genera un pdf basado en tablas
     *
     * @param tables        Lista con los nombre de las tablas. Estos nombres serán
     *                      la key de los siguientes mapas.
     * @param colsByTables  Mapa con las columnas de cada una de las tablas de la
     *                      lista. key=nombre de tabla.
     * @param valuesByTable Mapa con una lista de mapas. Se obtienen los datos de
     *                      una tabla en el primer mapa a partir del nombre de la
     *                      tabla y en el segundo nivel, se obtienen los datos de
     *                      una columna a partir de su nombre
     * @param fichero       Ruta absoluta del fichero de salida donde se generara el
     *                      pdf
     * @return fichero generado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File generatePdf(List<String> tables, Map<String, List<String>> colsByTables,
                                   Map<String, List<Map<String, String>>> valuesByTable, String fichero)
            throws FileNotFoundException, IOException {

        PdfWriter writer = new PdfWriter(fichero);
        PdfDocument pdf = new PdfDocument(writer);
        Document exportacionPdf = new Document(pdf, PageSize.A4.rotate());
        boolean firstTable = true;
        for (String sheetName : tables) {
            createTable(exportacionPdf, sheetName, colsByTables.get(sheetName), valuesByTable.get(sheetName),
                    !firstTable);
            firstTable = false;

        }
        exportacionPdf.close();
        return new File(fichero);
    }

    private static void createTable(Document exportacionPDF, String name, List<String> colNames,
                                    List<Map<String, String>> values, boolean addPageBreak) {

        int colSize = Math.round(PageSize.A2.getHeight() / colNames.size());

        Table table = new Table(colNames.size());

        // Iteramos por las columnas y luego por los valores
        for (String colName : colNames) {
            table.addHeaderCell(new Cell().addStyle(createHeaderCellStyle(colSize)).add(new Paragraph(colName)));
        }
        for (Map<String, String> value : values) {
            for (String columna : colNames) {
                table.addCell(new Cell().addStyle(createCellStyle(colSize))
                        .add(new Paragraph(value.get(columna) != null ? value.get(columna) : "")));
            }
        }
        if (addPageBreak) {
            exportacionPDF.add(new AreaBreak());
        }
        exportacionPDF.add(table);

    }

    private static Style createHeaderCellStyle(int colSize) {
        return createCellStyle(colSize, new DeviceRgb(102, 102, 153), HorizontalAlignment.CENTER);
    }

    private static Style createCellStyle(int colSize) {
        return createCellStyle(colSize, ColorConstants.WHITE, HorizontalAlignment.LEFT);
    }

    private static Style createCellStyle(int colSize, Color backgroundColor, HorizontalAlignment horizontalAlignment) {
        Style style = new Style();
        style.setBackgroundColor(backgroundColor);
        style.setHorizontalAlignment(horizontalAlignment);
        style.setVerticalAlignment(VerticalAlignment.MIDDLE);
        style.setWidth(colSize);
        return style;

    }
}
