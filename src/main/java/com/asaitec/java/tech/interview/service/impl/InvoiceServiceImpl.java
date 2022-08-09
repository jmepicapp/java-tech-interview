package com.asaitec.java.tech.interview.service.impl;

import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;
import com.asaitec.java.tech.interview.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    OfferServiceImpl offerService;

    @Override
    public Map<String, FruitInvoiceItemDTO> setFruitListFromFile(FileReader file) {
        //TODO: refactor
        String line = "";
        String splitBy = ",";
        Map<String, FruitInvoiceItemDTO> fruitList = new HashMap<String, FruitInvoiceItemDTO>();
        try {
            BufferedReader br = new BufferedReader(file);
            while ((line = br.readLine()) != null) {
                //The first line contains the titles of the table, so we skip it
                if (line.split(splitBy)[1].equalsIgnoreCase("product")){
                    br.readLine();
                }
                String[] fruitLine = line.split(splitBy);
                //Create a new fruit object with the name and the int quantity
                FruitInvoiceItemDTO fruit = new FruitInvoiceItemDTO(fruitLine[1], Integer.valueOf(fruitLine[2]));
                fruitList.put(fruit.getFruitName(), fruit);
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return fruitList;
    }

    @Override
    public Map<String, FruitInvoiceItemDTO> setFruitPrizesFromFile(FileReader file) {
        //TODO: refactor
        String line = "";
        String splitBy = ",";
        Map<String, FruitInvoiceItemDTO> fruitList = new HashMap<String, FruitInvoiceItemDTO>();
        try {
            BufferedReader br = new BufferedReader(file);
            while ((line = br.readLine()) != null) {
                //The first line contains the titles of the table, so we skip it
                if (line.split(splitBy)[1].equalsIgnoreCase("product")){
                    br.readLine();
                }
                String[] fruitLine = line.split(splitBy);
                //Create a new fruit object with the name and the fruit prize
                FruitInvoiceItemDTO fruit = new FruitInvoiceItemDTO(fruitLine[1], Double.valueOf(fruitLine[2]));
                fruitList.put(fruit.getFruitName(), fruit);
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return fruitList;
    }

    @Override
    public FileReader createInvoice() throws FileNotFoundException {
        Map<String, FruitInvoiceItemDTO> orderList = new HashMap<String, FruitInvoiceItemDTO>();
        try {
            //Mocking the file location
            FileReader prizesFile = new FileReader("C:\\fruitPrice.csv");
            FileReader orderFile = new FileReader("C:\\fruitOrder.csv");
            Map<String, FruitInvoiceItemDTO> prizes = setFruitPrizesFromFile(prizesFile);
            Map<String, FruitInvoiceItemDTO> quantities = setFruitListFromFile(orderFile);
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
}
