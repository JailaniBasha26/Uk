package com.uk.uk.implementation;

import com.uk.uk.entity.PricingInsightsDAO;
import com.uk.uk.entity.DashboardGridDataDAO;
import com.uk.uk.entity.DashboardGridPriceUrlDAO;
import com.uk.uk.entity.ProductMasterDataDAO;
import com.uk.uk.repository.ProductMasterDataRepo;
import com.uk.uk.repository.PricingInsightsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PricingInsightsImpl {
    @Autowired
    private ProductMasterDataRepo ProductMasterDataRepo;
    @Autowired
    private PricingInsightsRepo PricingInsightsRepo;

    public List<ProductMasterDataDAO> getProductMasterByTag(Integer tag) {
        List<ProductMasterDataDAO> productMasterDataDAOList = new ArrayList<>();
        productMasterDataDAOList = ProductMasterDataRepo.getProductMasterDataByTagNo(tag);
        return productMasterDataDAOList;
    }

    public List<DashboardGridDataDAO> getGridData() {
        List<PricingInsightsDAO> productInsightsDAOList = new ArrayList<>();
        productInsightsDAOList = PricingInsightsRepo.getAll();

        Map<Integer, List<PricingInsightsDAO>> productGroupByTag = productInsightsDAOList.stream().collect(Collectors.groupingBy(PricingInsightsDAO::getTag));

        List<DashboardGridDataDAO> gridDataList = new ArrayList<>();

        productGroupByTag.forEach((tag, pricingInsightsList) -> {

            DashboardGridDataDAO gridData = new DashboardGridDataDAO();
            List<ProductMasterDataDAO> productMasterDataDAOList = ProductMasterDataRepo.getProductMasterDataByTagNo(tag);
            ProductMasterDataDAO productMasterDataDAO = productMasterDataDAOList.getFirst();

            AtomicReference<Integer> idx = new AtomicReference<>(0);

            pricingInsightsList.forEach(pricingInsights -> {

                        System.out.println(pricingInsights.getTag());

                        if (idx.get().intValue() == 0) {
                            gridData.setTag(productMasterDataDAO.getTag());
                            gridData.setCategory(productMasterDataDAO.getCategory());
                            gridData.setProductName(productMasterDataDAO.getProductName());
                            gridData.setQuantity(productMasterDataDAO.getQuantity());
                            gridData.setMeasurement(productMasterDataDAO.getMeasurement());
                        }

                        DashboardGridPriceUrlDAO dashboardGridPriceUrlDAO = new DashboardGridPriceUrlDAO();
                        dashboardGridPriceUrlDAO.setPrice(pricingInsights.getPrice());
                        dashboardGridPriceUrlDAO.setUrl(pricingInsights.getUrl());

                        switch (pricingInsights.getShopName()) {
                            case "ASDA":
                                gridData.setAsdaDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Morrisons":
                                gridData.setMorrisonsDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Tesco":
                                gridData.setTescoDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Sainsburys":
                                gridData.setSainsburysDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Coop":
                                gridData.setCoopDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Ocado":
                                gridData.setOcadoDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Waitrose":
                                gridData.setWaitroseDetails(dashboardGridPriceUrlDAO);
                                break;
                            case "Amazon":
                                gridData.setAmazonDetails(dashboardGridPriceUrlDAO);
                                break;
                        }
                        idx.getAndSet(idx.get() + 1);
                    }
            );
            gridDataList.add(gridData);
        });

        return gridDataList;
    }

    public Boolean deletePricingInsightsByTag(Integer tag) {
        Boolean deleteStatus = false;

        try {
            PricingInsightsRepo.deletePricingInsightsByTag(tag);
            deleteStatus = true;
        } catch (Exception e) {
            System.out.println("Delete Pricing Insights By Tag Error : " + e);
            deleteStatus = false;
        }

        return deleteStatus;

    }
}
