package com.example.project.controller;

import java.util.List;

import com.example.project.domain.dto.request.ProductCreateRequest;
import com.example.project.domain.dto.response.ProductResponse;
import com.example.project.domain.entities.Product;
import com.example.project.domain.mapper.ProductMapper;
import com.example.project.service.ProductService;
import com.example.project.utils.DownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper eventoMapper) {
        this.productService = productService;
        this.mapper = eventoMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<Product> list(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    // @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, consumes
    // = "multipart/form-data")
    // public ResponseEntity<String> upload(@RequestParam("file") MultipartFile
    // file, @PathVariable Integer id) {

    // String pathImage = downloadService.upload(file);

    // productService.uploadImage(pathImage, id);

    // return new ResponseEntity<>(productService.upload(file, id), HttpStatus.OK);
    // }

    @PostMapping(value = "/upload/{id}")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {

        DownloadService downloadService = new DownloadService();
        String pathImage = downloadService.upload(file);

        productService.uploadImage(pathImage, id);

        return ResponseEntity.ok(pathImage);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> post(@RequestBody ProductCreateRequest model) {

        Product product = productService.create(mapper.fromDto(model));

        return ResponseEntity.ok(mapper.toDto(product));
    }

    @PostMapping(value = "/image")
    public ResponseEntity<String> insertExcel(@RequestParam("file") MultipartFile file) {

        DownloadService downloadService = new DownloadService();
        String pathImage = downloadService.upload(file);

        productService.excelInserQuery(pathImage);

        return ResponseEntity.ok(pathImage);
    }

}