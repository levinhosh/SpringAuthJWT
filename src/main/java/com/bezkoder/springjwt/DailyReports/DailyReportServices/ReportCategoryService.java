package com.bezkoder.springjwt.DailyReports.DailyReportServices;


import com.bezkoder.springjwt.DailyReports.Enums.ReportCategorys;
import com.bezkoder.springjwt.DailyReports.Repository.ReportCategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportCategoryService {

     @Autowired
    private ReportCategoryRepo reportCategoryRepo;

    private ReportCategorys reportCategorys;

    public List<ReportCategorys> getAllCategoryNames(){

         try {
            return reportCategoryRepo.findAll();
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }

    public Optional<ReportCategorys> getCategoryById(Long id){

        try {
            return Optional.ofNullable(reportCategoryRepo.findById(id).orElse(null));
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }

    }


    public ReportCategorys addCategory(ReportCategorys reportCategorys) {
        try {
            reportCategorys.setReportCategory(reportCategorys.getReportCategory());
            //clientNameEnums.setClientName(String.valueOf(clientNameEnums));
            ReportCategorys newClientNameEnums= reportCategoryRepo.save(reportCategorys);
            return newClientNameEnums;
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }
}
