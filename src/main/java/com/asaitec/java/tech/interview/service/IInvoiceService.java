package com.asaitec.java.tech.interview.service;

import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public interface IInvoiceService {

    Map<String, FruitInvoiceItemDTO> setFruitListFromFile(FileReader file);

    Map<String, FruitInvoiceItemDTO> setFruitPrizesFromFile(FileReader file);

    FileReader createInvoice() throws FileNotFoundException;
}
