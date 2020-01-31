package com.example.project.domain.mapper;

import com.example.project.domain.dto.request.ProductCreateRequest;
import com.example.project.domain.dto.response.ProductResponse;
import com.example.project.domain.entities.Product;
import com.example.project.domain.entities.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper mapper;

    @Autowired
    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductResponse toDto(Product input) {
        return mapper.map(input, ProductResponse.class);
    }

    public Product fromDto(ProductCreateRequest input) {

        Product model = mapper.map(input, Product.class);

        Supplier supplier = new Supplier();

        supplier.setSupplierId(input.getSupplierId());
        model.setSupplierId(supplier);

        return model;
    }

}