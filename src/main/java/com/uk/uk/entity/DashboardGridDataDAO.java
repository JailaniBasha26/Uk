package com.uk.uk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;

import com.uk.uk.entity.DashboardGridPriceUrlDAO;

@Getter
@Setter
@Cacheable
@NoArgsConstructor
public class DashboardGridDataDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer no;
    public Integer tag;
    public String category;
    public String productName;
    public Integer quantity;
    public String measurement;
    public DashboardGridPriceUrlDAO asdaDetails;
    public DashboardGridPriceUrlDAO morrisonsDetails;
    public DashboardGridPriceUrlDAO sainsburysDetails;
    public DashboardGridPriceUrlDAO tescoDetails;
    public DashboardGridPriceUrlDAO coopDetails;
    public DashboardGridPriceUrlDAO ocadoDetails;
    public DashboardGridPriceUrlDAO waitroseDetails;
    public DashboardGridPriceUrlDAO amazonDetails;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public DashboardGridPriceUrlDAO getAsdaDetails() {
        return asdaDetails;
    }

    public void setAsdaDetails(DashboardGridPriceUrlDAO asdaDetails) {
        this.asdaDetails = asdaDetails;
    }

    public DashboardGridPriceUrlDAO getMorrisonsDetails() {
        return morrisonsDetails;
    }

    public void setMorrisonsDetails(DashboardGridPriceUrlDAO morrisonsDetails) {
        this.morrisonsDetails = morrisonsDetails;
    }

    public DashboardGridPriceUrlDAO getSainsburysDetails() {
        return sainsburysDetails;
    }

    public void setSainsburysDetails(DashboardGridPriceUrlDAO sainsburysDetails) {
        this.sainsburysDetails = sainsburysDetails;
    }

    public DashboardGridPriceUrlDAO getTescoDetails() {
        return tescoDetails;
    }

    public void setTescoDetails(DashboardGridPriceUrlDAO tescoDetails) {
        this.tescoDetails = tescoDetails;
    }

    public DashboardGridPriceUrlDAO getCoopDetails() {
        return coopDetails;
    }

    public void setCoopDetails(DashboardGridPriceUrlDAO coopDetails) {
        this.coopDetails = coopDetails;
    }

    public DashboardGridPriceUrlDAO getOcadoDetails() {
        return ocadoDetails;
    }

    public void setOcadoDetails(DashboardGridPriceUrlDAO ocadoDetails) {
        this.ocadoDetails = ocadoDetails;
    }

    public DashboardGridPriceUrlDAO getWaitroseDetails() {
        return waitroseDetails;
    }

    public void setWaitroseDetails(DashboardGridPriceUrlDAO waitroseDetails) {
        this.waitroseDetails = waitroseDetails;
    }

    public DashboardGridPriceUrlDAO getAmazonDetails() {
        return amazonDetails;
    }

    public void setAmazonDetails(DashboardGridPriceUrlDAO amazonDetails) {
        this.amazonDetails = amazonDetails;
    }
}
