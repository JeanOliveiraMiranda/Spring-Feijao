package com.example.project.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer orderId;

    @Column(name = "OrderDate", nullable = false)
    private Date orderdate;

    @Column(name = "OrderNumber", nullable = false, length = 10, columnDefinition = "nvarchar")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customerId;

    @Column(name = "TotalAmount", nullable = false, columnDefinition = "decimal(12,2)")
    private double totalAmount;
}