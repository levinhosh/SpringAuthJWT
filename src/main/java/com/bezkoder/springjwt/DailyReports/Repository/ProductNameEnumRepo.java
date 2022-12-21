package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnum;
import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnums;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductNameEnumRepo  extends JpaRepository<ProductNameEnums, Long> {
    Optional<ProductNameEnums> findByProductName(ProductNameEnum productNameEnum);

      Optional<ProductNameEnums> findById(Long id);

    @Override
    List<ProductNameEnums> findAll();
}



