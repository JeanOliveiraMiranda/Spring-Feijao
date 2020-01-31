package com.example.project.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.project.domain.entities.Product;
import com.example.project.domain.entities.Supplier;
import com.example.project.domain.mapper.ProductMapper;

public class ApachePOIExcelRead {

    private static final String FILE_NAME = "\\temp\\decola.xlsx";

    private final ProductMapper mapper;

    @Autowired
    public ApachePOIExcelRead(ProductMapper productMapper) {
        this.mapper = productMapper;
    }

    public static void main(String[] args) {

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            List<String> tables = new LinkedList<String>();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                // String values = "";
                List<String> values = new LinkedList<String>();

                while (cellIterator.hasNext()) {
                    // System.out.print(currentRow.getRowNum());

                    Cell currentCell = cellIterator.next();
                    // getCellTypeEnum shown as deprecated for version 3.15
                    // getCellTypeEnum ill be renamed to getCellType starting from version 4.0

                    // PEGAR NOME DAS COLUNAS
                    if (currentRow.getRowNum() == 0) {
                        if (currentCell.getColumnIndex() > 0) {
                            tables.add(currentCell.getStringCellValue());
                        }

                    } else {

                        // String tableName = tables.get(currentCell.getColumnIndex() - 1);

                        if (currentCell.getCellType() == CellType.STRING) {
                            values.add(currentCell.getStringCellValue());
                        }

                        else if (currentCell.getCellType() == CellType.NUMERIC) {
                            // verificar se não é inteiro
                            if (Double.toString(currentCell.getNumericCellValue()).contains(",")) {

                                values.add(Double.toString(currentCell.getNumericCellValue()).replace(",", "."));
                            } else {

                                values.add(Integer.toString((int) currentCell.getNumericCellValue()));
                            }

                        }

                        // Ver ultima celula verificada
                        // :: TO DO
                        if (currentCell.getColumnIndex() == 5) {

                            System.out.println(values.get(1));
                            System.out.println(values.get(2));
                            System.out.println(values.get(3));
                            System.out.println(values.get(4));
                            System.out.println(values.get(5));

                        }
                    }
                }
            }
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}