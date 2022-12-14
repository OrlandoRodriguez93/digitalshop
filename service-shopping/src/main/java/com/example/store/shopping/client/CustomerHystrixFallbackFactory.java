package com.example.store.shopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.store.shopping.model.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

    @Override
    public ResponseEntity<Customer> get(Long id) {
        Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }

}
