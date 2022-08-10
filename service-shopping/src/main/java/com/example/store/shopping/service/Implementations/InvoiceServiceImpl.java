package com.example.store.shopping.service.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.shopping.client.CustomerClient;
import com.example.store.shopping.client.ProductClient;
import com.example.store.shopping.entity.Invoice;
import com.example.store.shopping.entity.InvoiceItem;
import com.example.store.shopping.model.Customer;
import com.example.store.shopping.model.Product;
import com.example.store.shopping.repository.InvoiceRepository;
import com.example.store.shopping.service.InvoiceService;
import com.netflix.discovery.converters.Auto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerClient customerClient;

    @Auto
    ProductClient productClient;

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice get(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (null != invoice) {
            Customer customer = customerClient.get(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.get(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice;
    }

    @Override
    public Invoice create(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null) {
            return invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.updateStock(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });

        return invoiceDB;
    }

    @Override
    public Invoice update(Invoice invoice) {
        Invoice invoiceDB = get(invoice.getId());
        if (invoiceDB == null) {
            return null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice delete(Invoice invoice) {
        Invoice invoiceDB = get(invoice.getId());
        if (invoiceDB == null) {
            return null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

}
