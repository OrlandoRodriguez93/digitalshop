package com.example.store.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.store.shopping.entity.Invoice;

@Service
public interface InvoiceService {
    public List<Invoice> findAll();

    public Invoice get(Long id);

    public Invoice create(Invoice invoice);

    public Invoice update(Invoice invoice);

    public Invoice delete(Invoice invoice);
}
