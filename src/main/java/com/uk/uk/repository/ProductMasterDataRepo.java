package com.uk.uk.repository;

import com.uk.uk.entity.ProductMasterDataDAO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface ProductMasterDataRepo extends JpaRepository<ProductMasterDataDAO, Integer> {

    @Query(value = "SELECT * FROM ProductMasterData where ShopName=?1 and TagStatus=1", nativeQuery = true)
    List<ProductMasterDataDAO> getProductMasterDataByShopName(String shopName);
}
