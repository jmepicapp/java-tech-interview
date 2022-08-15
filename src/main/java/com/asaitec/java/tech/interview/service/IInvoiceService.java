package com.asaitec.java.tech.interview.service;

import com.asaitec.java.tech.interview.dto.FruitDTO;
import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public interface IInvoiceService {

    Map<FruitDTO, FruitInvoiceItemDTO> setFruitListFromFile(FileReader file);

    Map<FruitDTO, FruitInvoiceItemDTO> setFruitPrizesFromFile(FileReader file);

    FileReader createInvoice() throws FileNotFoundException;
}
