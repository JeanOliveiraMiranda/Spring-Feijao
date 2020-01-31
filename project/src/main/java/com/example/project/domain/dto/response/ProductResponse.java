package com.example.project.domain.dto.response;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.project.domain.entities.Supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Integer id;

    private String productName;

    private Supplier supplierId;

    private double unitPrice;

    private String pacote;

    private Integer isDiscontinued;

    private String image;
}