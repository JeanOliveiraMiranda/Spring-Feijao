package com.example.project.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private CustomerOrder orderId;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product productId;

    @Column(name = "UnitPrice", nullable = false, columnDefinition = "decimal(12,2)")
    private double unitPrice;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;
}