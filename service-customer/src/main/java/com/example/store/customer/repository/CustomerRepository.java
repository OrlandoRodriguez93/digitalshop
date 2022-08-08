package com.example.store.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.customer.entity.Customer;
import com.example.store.customer.entity.Region;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByNumberID(String numberId);

    public List<Customer> findByLastName(String lastName);

    public List<Customer> findByRegion(Region region);

}
