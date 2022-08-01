package com.example.store.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.store.customer.entity.Customer;
import com.example.store.customer.entity.Region;

@Service
public interface CustomerService {

    public List<Customer> findAll();

    public List<Customer> findByRegion(Region region);

    public Customer get(Long id);

    public Customer create(Customer customer);

    public Customer update(Customer customer);

    public Customer delete(Customer customer);

}
