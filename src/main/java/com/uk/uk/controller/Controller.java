package com.uk.uk.controller;

import com.uk.uk.entity.DashboardGridDataDAO;
import com.uk.uk.entity.ProductMasterDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uk.uk.repository.PricingInsightsRepo;

import com.uk.uk.implementation.AsdaImpl;
import com.uk.uk.implementation.MorrisonsImpl;
import com.uk.uk.implementation.SainsburysImpl;
import com.uk.uk.implementation.TescoImpl;
import com.uk.uk.implementation.ProductMasterDataImpl;
import com.uk.uk.implementation.PricingInsightsImpl;

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
    private TescoImpl TescoImpl;
    @Autowired
    private ProductMasterDataImpl ProductMasterDataImpl;
    @Autowired
    private PricingInsightsImpl PricingInsightsImpl;

    @GetMapping("/insertPricingInsights")
    public Boolean insertPricingInsights() throws IOException, InterruptedException {
        AsdaImpl.getProductDetails();
        MorrisonsImpl.getProductDetails();
        SainsburysImpl.getProductDetails();
        TescoImpl.getProductDetails();
        return true;
    }

    @PostMapping("/insertProductMasterData")
    public Boolean insertProductMasterData(@RequestBody List<ProductMasterDataDAO> ProductMasterDataDAOList) throws IOException, InterruptedException {
        ProductMasterDataImpl.insertProductMasterData(ProductMasterDataDAOList);
        return true;
    }

    @GetMapping("/getGridData")
    public List<DashboardGridDataDAO> getUser() {
        List<DashboardGridDataDAO> gridDataList = new ArrayList<>();
        gridDataList = PricingInsightsImpl.getGridData();
        return gridDataList;
    }

    @GetMapping("/getProductMasterByTag")
    public List<ProductMasterDataDAO> getProductMasterByTag(@RequestParam("tag") Integer tag) {
        List<ProductMasterDataDAO> productMasterDataDAOList = new ArrayList<>();
        productMasterDataDAOList = PricingInsightsImpl.getProductMasterByTag(tag);
        return productMasterDataDAOList;
    }

    @GetMapping("/hideProductByTag")
    public Boolean hideProductByTag(@RequestParam("tag") Integer tag) {
        ProductMasterDataImpl.hideProductByTag(tag);
        PricingInsightsImpl.deletePricingInsightsByTag(tag);
        return true;
    }
}
