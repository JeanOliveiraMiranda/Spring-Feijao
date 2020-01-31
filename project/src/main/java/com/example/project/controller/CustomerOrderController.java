package com.example.project.controller;

import java.util.List;

import com.example.project.domain.entities.CustomerOrder;
import com.example.project.service.CustomerOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")

public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<CustomerOrder>> list() {
        return ResponseEntity.ok(customerOrderService.list());
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<CustomerOrder> list(@PathVariable Integer id) {
        return ResponseEntity.ok(customerOrderService.findById(id));
    }
}