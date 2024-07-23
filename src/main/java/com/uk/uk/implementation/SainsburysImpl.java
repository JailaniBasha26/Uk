package com.uk.uk.implementation;

import com.uk.uk.entity.ProductMasterDataDAO;
import com.uk.uk.repository.PricingInsightsRepo;
import com.uk.uk.repository.ProductMasterDataRepo;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class SainsburysImpl {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ProductMasterDataRepo ProductMasterDataRepo;
    @Autowired
    PricingInsightsRepo ProductInsightsRepo;

    public void getProductDetails() throws InterruptedException {
        List<ProductMasterDataDAO> productMasterDataList = new ArrayList<>();

        // Filter the ProductMasterData table for "Sainsburys"
        productMasterDataList = ProductMasterDataRepo.getProductMasterDataByShopName("Sainsburys");

        // Set up ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Hide Chrome GUI

        // Open the Chrome Driver
        WebDriver driver = new ChromeDriver(options);

        Integer idx = 0;

        for (ProductMasterDataDAO productMasterData : productMasterDataList) {
            insertPricingInsights(productMasterData, driver, idx);
        }
        driver.close();
    }

    public void insertPricingInsights(ProductMasterDataDAO productMasterData, WebDriver driver, Integer idx) throws InterruptedException {

        // Navigate to the product url
        driver.get(productMasterData.getUrl());

        //Current Timestamp
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // If the idx value is 0, then we need to click the "I Accept" cookie button
        if (idx == 0) {
            // Wait for 3 sec to load the Cookie tag in browser
            Thread.sleep(3000);

            try {
                // Locate the cookie button by Id
                WebElement cookieButton = driver.findElement(By.id("onetrust-accept-btn-handler"));

                // Click on the cookie button
                cookieButton.click();
            } catch (Exception e) {
                System.out.println("Error in Cookie Button");
            }
        }

        // Wait for 3 sec for closing the Cookie tag
        Thread.sleep(3000);

        try {

            // Get the price by using the className
            String itemPriceString = driver.findElement(By.className("pd__cost__retail-price")).getText().split("£")[1];

            // Convert the price string to double
            Double itemPrice = Double.parseDouble(itemPriceString);

            // Locate the image element
            WebElement imageElement = driver.findElement(By.cssSelector(".pd__image"));

            // Get the src attribute value
            String imageRef = imageElement.getAttribute("src");

            //Insert into PricingInsights table
            ProductInsightsRepo.insertPricingInsights(productMasterData.getNo(), productMasterData.getTag(),
                    "Sainsburys", itemPrice, productMasterData.getUrl(), true, now, imageRef);

        } catch (Exception e) {
            //Insert into PricingInsights table
            ProductInsightsRepo.insertPricingInsights(productMasterData.getNo(), productMasterData.getTag(),
                    "Sainsburys", 0.0, productMasterData.getUrl(), false, now, "");

            System.out.println("Error URL :" + productMasterData.getUrl());
        }
        idx++;
    }

}
