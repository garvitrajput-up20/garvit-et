package com.garvitrajput.exittest.dao;

import java.util.List;
import com.garvitrajput.exittest.entity.ProductStructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<ProductStructure, Integer> {

    List<ProductStructure> findByProductName(String productName);

    List<ProductStructure> findByProductCode(String productCode);

    List<ProductStructure> findByBrand(String brand);

    List<ProductStructure> findByProductNameAndProductCode(String productName, String productCode);

    List<ProductStructure> findByProductNameAndBrand(String productName, String brand);

    List<ProductStructure> findByProductCodeAndBrand(String productCode, String brand);

    List<ProductStructure> findByProductNameAndProductCodeAndBrand(String productName, String productCode, String brand);
    
   
}
