package com.example.project.controller;

import java.util.List;

import com.example.project.domain.entities.OrderItem;
import com.example.project.service.OrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderItem>> list() {
        return ResponseEntity.ok(orderItemService.list());
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<OrderItem> list(@PathVariable Integer id) {
        return ResponseEntity.ok(orderItemService.findById(id));
    }
}