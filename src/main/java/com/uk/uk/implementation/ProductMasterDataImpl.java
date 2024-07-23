package com.uk.uk.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uk.uk.entity.ProductMasterDataDAO;
import com.uk.uk.repository.ProductMasterDataRepo;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMasterDataImpl {
    @Autowired
    private ProductMasterDataRepo ProductMasterDataRepo;
    @Autowired
    private AsdaImpl AsdaImpl;
    @Autowired
    private MorrisonsImpl MorrisonsImpl;
    @Autowired
    private SainsburysImpl SainsburysImpl;
    @Autowired
    private TescoImpl TescoImpl;

    public Boolean insertProductMasterData(List<ProductMasterDataDAO> ProductMasterDataDAOList) throws JsonProcessingException, InterruptedException {

        Integer tagMaxNo;
        tagMaxNo = ProductMasterDataRepo.getMaxTagNo();

        if (null != tagMaxNo)
            tagMaxNo++;
        else
            tagMaxNo = 1;

        for (ProductMasterDataDAO productMasterData : ProductMasterDataDAOList) {
            ProductMasterDataRepo.insertProductMasterData(productMasterData.getProductName(), productMasterData.getQuantity(),
                    productMasterData.getMeasurement(), productMasterData.getShopName(), productMasterData.getUrl(),
                    productMasterData.getCategory(), tagMaxNo, true);
        }

        List<ProductMasterDataDAO> productMasterDataDAOListByTagNo = new ArrayList<>();

        productMasterDataDAOListByTagNo = ProductMasterDataRepo.getProductMasterDataByTagNo(tagMaxNo);

        for (ProductMasterDataDAO productMasterData : productMasterDataDAOListByTagNo) {

            String shopName = productMasterData.getShopName();

            switch (shopName) {
                case "ASDA":
                    AsdaImpl.insertPricingInsights(productMasterData);
                    break;
                case "Morrisons":
                    MorrisonsImpl.insertPricingInsights(productMasterData);
                    break;
                case "Sainsburys":
                case "Tesco":
                    WebDriver driver = openChromDriver();
                    if (shopName.equalsIgnoreCase("Sainsburys"))
                        SainsburysImpl.insertPricingInsights(productMasterData, driver, 0);
                    else
                        TescoImpl.insertPricingInsights(productMasterData, driver);
                    driver.close();
                    break;
            }
        }
        return true;
    }

    private WebDriver openChromDriver() {
        // Set up ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();

        // Open the Chrome Driver
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    public Boolean hideProductByTag(Integer tag) {
        try {
            ProductMasterDataRepo.updateTagStatus(tag);
            return true;
        } catch (Exception e) {
            System.out.println("Hide Product By Tag Error : " + e);
            return false;
        }
    }
}
