package com.bezkoder.springjwt.DailyReports.Services;


import com.bezkoder.springjwt.DailyReports.DailyReports;
import com.bezkoder.springjwt.DailyReports.Repository.*;
import com.bezkoder.springjwt.DailyReports.Request.NewReportRequest;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DailyReportsService {

    @Autowired
    ClientNameEnumRepo clientNameEnumRepo;
    @Autowired
    DepartmentEnumRepo departmentEnumRepo;
    @Autowired
    ProductNameEnumRepo productNameEnumRepo;
    @Autowired
    ReportCategoryRepo reportCategoryRepo;

    private NewReportRequest newReportRequest;
    @Autowired
        private DailyReportsRepo dailyReportsRepo;
        @Autowired
        private UserRepository userRepository;

        private Optional<DailyReports> dailyReports;


        public List<DailyReports> getAllReports(){
        return (List<DailyReports>) dailyReportsRepo.findAll();
    }


    ////////////////////// LIST ALL REPORTS ///////////////////
    public List<DailyReports> readReport(Long userId) {

        try {

            Optional<User> appUser1 = userRepository.findById(userId);

            List<DailyReports> dailyReports1 = null;

            if (appUser1.isPresent()) {

                dailyReports1 = dailyReportsRepo.findByUniqueConstraint(userId);

                return (dailyReportsRepo.findByUniqueConstraint(userId));


            }
        } catch (Exception e) {
            log.info("Error{} " + e);
            return null;
        }


        return null;
    }

/////////////////////////// CREATION OF REPORT ///////////////////////////
    public DailyReports createReport(DailyReports dailyReports, Long user){
        try{
            Optional<User> appUser1= userRepository.findById(user);
            if(appUser1.isPresent()){
                User existingAppUser= appUser1.get();
                dailyReports.setUser(existingAppUser);
                DailyReports savedDailyReports=dailyReportsRepo.save(dailyReports);
                return savedDailyReports;
            }else {
                return null;
            }

        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }



    }


}
