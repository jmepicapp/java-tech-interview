package com.asaitec.java.tech.interview.service.impl;

import com.asaitec.java.tech.interview.dto.FruitDTO;
import com.asaitec.java.tech.interview.dto.FruitInvoiceItemDTO;
import com.asaitec.java.tech.interview.dto.OfferDTO;
import com.asaitec.java.tech.interview.error.FruitOfferException;
import com.asaitec.java.tech.interview.error.FruitQuantityException;
import com.asaitec.java.tech.interview.service.IOfferService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OfferServiceImpl implements IOfferService {


    @Override
    public OfferDTO getTotalOffer(Map<String, FruitInvoiceItemDTO> fruitList) {
        OfferDTO offerDTO = new OfferDTO();
        Iterator it = fruitList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, FruitInvoiceItemDTO> entry = (Map.Entry)it.next();
            String fruitName = entry.getKey();
            FruitInvoiceItemDTO fruit = entry.getValue();
            switch (fruitName){
                case "Apple":
                    offerDTO.fruitList.addAll(getAppleDiscount(fruit.getQuantity()));
                    break;
                case "Pear":
                    offerDTO.discount = getPearDiscount(fruit.getQuantity(), fruit.getUnitPrize());
                    offerDTO.fruitList.addAll(getFruitOfferbyPear(fruit.getQuantity()));
                    break;
                default:
                    throw new FruitOfferException("This fruit has any offer.");
            }
        }
        return offerDTO;
    }

    /* For every 4€ spent on Pears, we will deduct one euro from your final invoice */
    private double getPearDiscount(int pears, double pearsPrize) {
        if(pears > 0) {
            Double totalDiscount;
            totalDiscount = ((double) pears * pearsPrize) / 4;
            return totalDiscount.intValue();
        }
        throw new FruitQuantityException("Pears quantity is not valid");
    }

    /* Get a free orange for every 2 Pears you buy*/
    private List<FruitDTO> getFruitOfferbyPear(int pears) {
        if(pears > 0) {
            List<FruitDTO> fruitList = new ArrayList<FruitDTO>();
            while (pears % 2 > 1) {
                fruitList.add(new FruitDTO("Orange"));
                pears = pears/2;
            }
            return fruitList;
        }
        throw new FruitQuantityException("Pears quantity is not valid");
    }

    /* Buy 3 Apples and pay 2 */
    private List<FruitDTO> getAppleDiscount(int apples) {
        if(apples > 0) {
            List<FruitDTO> fruitList = new ArrayList<FruitDTO>();
            while (apples % 3 > 1) {
                fruitList.add(new FruitDTO("Apple"));
                apples = apples/3;
            }
            return fruitList;
        }
        throw new FruitQuantityException("Pears quantity is not valid");
    }
}
