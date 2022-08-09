package com.asaitec.java.tech.interview.service;

import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;
import com.asaitec.java.tech.interview.dto.OfferDTO;

import java.util.Map;

public interface IOfferService {

    OfferDTO getTotalOffer(Map<String, FruitInvoiceItemDTO> fruitList);
}
