package com.bezkoder.springjwt.DailyReports.Services;


import com.bezkoder.springjwt.DailyReports.DailyReports;
import com.bezkoder.springjwt.DailyReports.Repository.*;
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


    @Autowired
        private DailyReportsRepo dailyReportsRepo;
        @Autowired
        private UserRepository userRepository;

        private Optional<DailyReports> dailyReports;


        ////////////////////// LIST ALL REPORTS ///////////////////
        public List<DailyReports> getAllReports(){
        return (List<DailyReports>) dailyReportsRepo.findAll();
    }


    ////////////////////// LIST SPECIFIC USER ALL REPORTS ///////////////////
    public List<DailyReports> readReport(Long userId) {

        try {

            Optional<User> appUser = userRepository.findById(userId);

            //List<DailyReports> dailyReports = null;

            if (appUser.isPresent()) {

                List<DailyReports> dailyReports = dailyReportsRepo.findByUniqueConstraint(userId);

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
            Optional<User> appUser= userRepository.findById(user);
            if(appUser.isPresent()){
                User existingAppUser= appUser.get();
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

    //////////////////////////// UPDATE REPORT /////////////////////////////////////


    public DailyReports updateUserReport(Long id, DailyReports dr) {
         DailyReports dailyReports = dailyReportsRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("No such record in your Report Diary"));


            dailyReports.setReport_description(dr.getReport_description());
            dailyReports.setRept_FK(dr.getRept_FK());
            dailyReports.setPrdt_FK(dr.getPrdt_FK());
            dailyReports.setTicketId(dr.getTicketId());
            dailyReports.setClient_FK(dr.getClient_FK());
            dailyReports.setTimeTaken(dr.getTimeTaken());
            dailyReports.setDept_FK(dr.getDept_FK());


            return dailyReportsRepo.save(dailyReports);
    }

    //////////////////////////// DELETE REPORT ///////////////////////////////////

    public void deleteEmployee(Long rpt_id) {
            try {

                dailyReportsRepo.deleteById(rpt_id);
            }catch (Exception e){
                log.info("Error {}"+e);
            }
	}


}
