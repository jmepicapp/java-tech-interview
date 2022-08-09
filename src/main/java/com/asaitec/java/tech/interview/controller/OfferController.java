package com.asaitec.java.tech.interview.controller;

import com.asaitec.java.tech.interview.service.impl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
@RequestMapping(name = "/invoice")
public class OfferController {

    @Autowired
    public InvoiceServiceImpl invoiceService;

    @PostMapping
    public ResponseEntity<FileReader> getTotalInvoice() throws FileNotFoundException {
        FileReader invoice = this.invoiceService.createInvoice();
        return invoice != null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }
}
