package com.example.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.store.shopping.model.Customer;

@FeignClient(name = "service-customer", fallback = CustomerHystrixFallbakcFactory.class)
public interface CustomerClient {

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> get(@PathVariable(name = "id") Long id);

}
