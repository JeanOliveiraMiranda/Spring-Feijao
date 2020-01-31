package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CustomerOrder;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.CustomerOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    public List<CustomerOrder> list() {
        return orderRepository.findAll();
    }

    public CustomerOrder findById(Integer id) {
        Optional<CustomerOrder> customer = orderRepository.findById(id);

        return customer.orElseThrow(() -> new DataNotFoundException("Customer Not found"));

    }
}