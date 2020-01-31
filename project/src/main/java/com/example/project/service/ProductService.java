package com.example.project.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.dto.request.ProductCreateRequest;
import com.example.project.domain.entities.Product;
import com.example.project.domain.mapper.ProductMapper;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ProductRepository;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.mapper = productMapper;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);

        return product.orElseThrow(() -> new DataNotFoundException("Product Not found"));

    }

    public String upload(MultipartFile file, Integer id) {
        String dirName = File.separator + "temp";
        File dir = new File(dirName);

        if (!dir.exists())
            dir.mkdir();

        String filename = file.getOriginalFilename();
        File destFile = new File(dir + File.separator + filename);

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filename;
    }

    public void uploadImage(String pathImage, Integer id) {
        productRepository.updateImage(pathImage, id);
    }

    public void excelInserQuery(String pathFile) {
        final String FILE_NAME = pathFile;
        // final String FILE_NAME = "/temp/decola.xlsx";

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            List<String> tables = new LinkedList<String>();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                // Quarda uma array com as posições de cada célula
                List<String> values = new LinkedList<String>();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    // getCellTypeEnum shown as deprecated for version 3.15
                    // getCellTypeEnum ill be renamed to getCellType starting from version 4.0

                    if (currentRow.getRowNum() == 0) {

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
                        if (currentCell.getColumnIndex() == 5) {

                            ProductCreateRequest productCreateRequest = new ProductCreateRequest();

                            // productCreateRequest.setId(Integer.parseInt(values.get(0)));
                            productCreateRequest.setProductName(values.get(1));
                            productCreateRequest.setSupplierId(Integer.parseInt(values.get(2)));
                            productCreateRequest.setUnitPrice(Double.parseDouble(values.get(3)));
                            productCreateRequest.setPacote(values.get(4));
                            productCreateRequest.setIsDiscontinued(Integer.parseInt(values.get(5)));
                            productCreateRequest.setImage("");
                            Product product = mapper.fromDto(productCreateRequest);

                            productRepository.save(product);
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

    public Product create(Product model) {
        return productRepository.save(model);
    }
}