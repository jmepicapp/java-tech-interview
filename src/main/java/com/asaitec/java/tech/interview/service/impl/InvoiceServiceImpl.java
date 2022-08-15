package com.asaitec.java.tech.interview.service.impl;

import com.asaitec.java.tech.interview.dto.FruitDTO;
import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;
import com.asaitec.java.tech.interview.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    OfferServiceImpl offerService;

    @Override
    public Map<FruitDTO, FruitInvoiceItemDTO> setFruitListFromFile(FileReader file) {
        Map<FruitDTO, FruitInvoiceItemDTO> fruitList = new HashMap<FruitDTO, FruitInvoiceItemDTO>();
        Map<Integer, String[]> lines = this.getCSVLines(file);
        Iterator it = lines.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String[]> entry = (Map.Entry) it.next();
            String[] line = entry.getValue();
            //The first line contains the titles of the table, so we skip it
            if (entry.getKey() != 1) {
                //Create a new fruit object with the name and the fruit prize
                FruitDTO fruit = new FruitDTO(line[1]);
                FruitInvoiceItemDTO fruitInvoiceItem = new FruitInvoiceItemDTO(fruit, Integer.valueOf(line[2]));
                fruitList.put(fruit, fruitInvoiceItem);
            }
        }
        return fruitList;
    }

    @Override
    public Map<FruitDTO, FruitInvoiceItemDTO> setFruitPrizesFromFile(FileReader file) {
        Map<FruitDTO, FruitInvoiceItemDTO> fruitList = new HashMap<FruitDTO, FruitInvoiceItemDTO>();
        Map<Integer, String[]> lines = this.getCSVLines(file);
        Iterator it = lines.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String[]> entry = (Map.Entry) it.next();
            String[] line = entry.getValue();
            //The first line contains the titles of the table, so we skip it
            if (entry.getKey() != 1) {
                //Create a new fruit object with the name and the fruit prize
                FruitDTO fruit = new FruitDTO(line[1]);
                FruitInvoiceItemDTO fruitInvoiceItem = new FruitInvoiceItemDTO(fruit, Integer.valueOf(line[2]));
                fruitList.put(fruit, fruitInvoiceItem);
            }
        }
        return fruitList;
    }

    @Override
    public FileReader createInvoice() throws FileNotFoundException {
        Map<FruitDTO, FruitInvoiceItemDTO> orderList = new HashMap<FruitDTO, FruitInvoiceItemDTO>();
        try {
            //Mocking the file location
            FileReader prizesFile = new FileReader("C:\\fruitPrice.csv");
            FileReader orderFile = new FileReader("C:\\fruitOrder.csv");
            Map<FruitDTO, FruitInvoiceItemDTO> prizes = setFruitPrizesFromFile(prizesFile);
            Map<FruitDTO, FruitInvoiceItemDTO> quantities = setFruitListFromFile(orderFile);
            orderList.putAll(prizes);
            Iterator it = orderList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, FruitInvoiceItemDTO> entry = (Map.Entry)it.next();
                FruitInvoiceItemDTO fruit = entry.getValue();
                fruit.setQuantity(quantities.get(entry.getKey()).getQuantity());
            }

        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found");
        } finally {
            this.offerService.getTotalOffer(orderList);
        }
        //TODO: create file to return in the HTTP REQUEST
        return new FileReader("C:\\totalInvoice");
    };

    private Map<Integer, String[]> getCSVLines(FileReader file) {
        String line = "";
        String splitBy = ",";
        Map<Integer, String[]> csvLines = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(file);
            while ((line = br.readLine()) != null) {
                Integer lineNumber = 0;
                String[] lines = line.split(splitBy);
                csvLines.put(++lineNumber, lines);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvLines;
    }
}
