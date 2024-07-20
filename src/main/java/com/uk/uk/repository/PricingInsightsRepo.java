package com.uk.uk.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.uk.uk.entity.PricingInsightsDAO;

import java.sql.Timestamp;
import java.util.List;

public interface PricingInsightsRepo extends JpaRepository<PricingInsightsDAO, Integer> {
    @Query(value = "SELECT * FROM PricingInsights ", nativeQuery = true)
    List<PricingInsightsDAO> getAll();

    @Modifying
    @Query(value = "insert into PricingInsights (ProductMasterDataNo,Tag, Price,Availability,CreatedAtDateTime,ImageRef) " +
            "values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    @Transactional
    void insertPricingInsights(Integer productMasterDataNo, Integer tag, Double price, Boolean availability, Timestamp createdAtDateTime,
                               String imageRef);

}
