package com.bezkoder.springjwt.DailyReports.DailyReportServices;

import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnums;
import com.bezkoder.springjwt.DailyReports.Repository.ProductNameEnumRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductNameService {


    @Autowired
    private ProductNameEnumRepo productNameEnumRepo;

    private ProductNameEnums productNameEnums;

    public List<ProductNameEnums> getAllProductNames(){

         try {
            return productNameEnumRepo.findAll();
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }

    public Optional<ProductNameEnums> getProductById(Long id){

        try {
            return Optional.ofNullable(productNameEnumRepo.findById(id).orElse(null));
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }

    }


    public ProductNameEnums addProduct(ProductNameEnums productNameEnums) {
        try {
            productNameEnums.setProductName(productNameEnums.getProductName());
            //clientNameEnums.setClientName(String.valueOf(clientNameEnums));
            ProductNameEnums newClientNameEnums= productNameEnumRepo.save(productNameEnums);
            return newClientNameEnums;
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }
}
