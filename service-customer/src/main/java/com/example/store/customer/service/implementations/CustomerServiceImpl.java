package com.example.store.customer.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.customer.entity.Customer;
import com.example.store.customer.entity.Region;
import com.example.store.customer.repository.CustomerRepository;
import com.example.store.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer get(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer create(Customer customer) {
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer update(Customer customer) {
        Customer customerDB = get(customer.getId());
        if (customerDB == null)
            return null;

        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer delete(Customer customer) {
        Customer customerDB = get(customer.getId());
        if (customerDB == null)
            return null;

        customer.setState("DELETED");
        return customerRepository.save(customer);
    }

}
