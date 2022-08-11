package com.example.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.store.shopping.model.Product;

@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> get(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/products/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable(name = "id") Long id,
            @RequestParam(name = "quantity", required = true) Double quantity);

}
