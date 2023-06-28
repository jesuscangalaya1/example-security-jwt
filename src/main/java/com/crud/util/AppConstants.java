package com.crud.util;

import java.util.List;

public final class AppConstants {

    // CLIENT ERRORS
    public static final String BAD_REQUEST = "P-400";
    public static final String BAD_REQUEST_PRODUCT = "El Id del Producto no existe ";
    public static final String BAD_REQUEST_CATEGORY = "El Id de la Categoria no existe ";

    //MESSAGE CONTROLLER
    public static final String SUCCESS = "SUCCESS";
    public static final String MESSAGE_ID_PRODUCT = "PRODUCT ID: ";
    public static final String MESSAGE_ID_CATEGORY = "CATEGORY ID: ";


    // =============================================================================================
    // CONSTANTES DE PAGINATION
    // =============================================================================================
    public static final String NUMERO_DE_PAGINA_POR_DEFECTO = "0";
    public static final String MEDIDA_DE_PAGINA_POR_DEFECTO = "10";
    public static final String ORDENAR_POR_DEFECTO = "id";
    public static final String ORDENAR_DIRECCION_POR_DEFECTO = "asc";

    // =============================================================================================
    // TIPOS DE FORMATOS DE ARCHIVOS
    // =============================================================================================
    /** FORMATO_ARCHIVOS */
    public static final String FORMAT_EXCEL = ".xlsx";
    public static final String FORMAT_PDF = ".pdf";
    public static final String FORMATO_EXCEL_ABREVIATURA = "EXCEL";
    public static final String FORMATO_PDF_ABREVIATURA = "PDF";
    public static final List<String> ARRAY_FORMATO = List.of(FORMATO_EXCEL_ABREVIATURA,FORMATO_PDF_ABREVIATURA);

    /** ERROR_REPORTE */
    public static final String ERROR_REPORTE = "Ocurrió un error al generar el reporte";

    // =============================================================================================
    // TIPOS DE FORMATOS DE ARCHIVOS
    // =============================================================================================
    /** FORMATO_ARCHIVOS */
    public static final String SHEET_PRODUCT = "Lista-Productos";
    public static final String VC_EMTY = "-";
    // =============================================================================================
    // NOMBRE DE REPORTES Y COLUMNAS DE 'PRODUCTOS'
    // =============================================================================================
    public static final String REPORT_NAME_PRODUCT_PAGINABLE = "report-producto";
    public static final String COL_PRODUCT_ID = "ID";
    public static final String COL_PRODUCT_NAME = "NAME";
    public static final String COL_PRODUCT_PRICE = "PRICE";
    public static final String COL_PRODUCT_DESCRIPTION = "DESCRIPTION";
    public static final String COL_CATEGORY_ID = "CATEGORY_ID";
    public static final String COL_CATEGORY_NAME = "CATEGORY_NAME";

}
