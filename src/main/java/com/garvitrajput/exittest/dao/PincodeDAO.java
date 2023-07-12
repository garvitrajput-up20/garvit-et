package com.garvitrajput.exittest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.garvitrajput.exittest.entity.PincodeStructure;

public interface PincodeDAO extends JpaRepository<PincodeStructure, Integer>{

    PincodeStructure findByPinCodeAndProduct_ProductId(String pinCode, int productId);

}
