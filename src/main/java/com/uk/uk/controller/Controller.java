package com.uk.uk.controller;

import com.uk.uk.entity.PricingInsightsDAO;
import com.uk.uk.implementation.TescoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uk.uk.repository.PricingInsightsRepo;

import com.uk.uk.implementation.AsdaImpl;
import com.uk.uk.implementation.MorrisonsImpl;
import com.uk.uk.implementation.SainsburysImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private PricingInsightsRepo ProductInsightsRepo;

    @Autowired
    private AsdaImpl AsdaImpl;
    @Autowired
    private MorrisonsImpl MorrisonsImpl;
    @Autowired
    private SainsburysImpl SainsburysImpl;
    @Autowired
    private com.uk.uk.implementation.TescoImpl TescoImpl;

    @GetMapping("/insertPricingInfoForAllShop")
    public Boolean start() throws IOException, InterruptedException {
        AsdaImpl.getProductDetails();
        MorrisonsImpl.getProductDetails();
        SainsburysImpl.getProductDetails();
        TescoImpl.getProductDetails();
        return true;
    }

    @GetMapping("/getGridData")
    public List<PricingInsightsDAO> getUser() {
        List<PricingInsightsDAO> productInsightsDAOList = new ArrayList<>();
        productInsightsDAOList = ProductInsightsRepo.getAll();

        return productInsightsDAOList;
    }


}
