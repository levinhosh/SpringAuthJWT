package com.bezkoder.springjwt.DailyReports.Services;


import com.bezkoder.springjwt.DailyReports.Repository.*;
import com.bezkoder.springjwt.DailyReports.SecondDailyReport;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SecondDailyReportsService {

    @Autowired
    ClientNameEnumRepo clientNameEnumRepo;
    @Autowired
    DepartmentEnumRepo departmentEnumRepo;
    @Autowired
    ProductNameEnumRepo productNameEnumRepo;
    @Autowired
    ReportCategoryRepo reportCategoryRepo;

     @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
        SecondDailyReportRepo dailyReportsRepo;
        @Autowired
        UserRepository userRepository;

        Optional<SecondDailyReportRepo> dailyReports;

        ////////////////////// LIST ALL REPORTS ///////////////////
        public List<SecondDailyReport> getAllReports(){
        return dailyReportsRepo.findAll();
    }


    ////////////////////// LIST SPECIFIC USER ALL REPORTS ///////////////////
    public List<SecondDailyReport> readReport(Long userId) {

        try {

            Optional<User> appUser = userRepository.findById(userId);

            //List<DailyReports> dailyReports = null;

            if (appUser.isPresent()) {

                List<SecondDailyReport> dailyReports = dailyReportsRepo.findByUniqueConstraint(userId);

                return dailyReports;


            }
        } catch (Exception e) {
            log.info("Error{} " + e);
            return null;
        }


        return null;
    }

/////////////////////////// CREATION OF REPORT ///////////////////////////
    public ResponseEntity<SecondDailyReport> createReport(Long user, SecondDailyReport dailyReports){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            //System.out.println(auth);

            Optional<User> appUser= userRepository.findById(user);
           if(appUser.isPresent()){
//                User existingAppUser= appUser.get();

                User existingAppUser= appUser.get();
                dailyReports.setUser(existingAppUser);

               SecondDailyReport savedDailyReports=dailyReportsRepo.save(dailyReports);
               return ResponseEntity.ok(savedDailyReports);
           }else {
              return (ResponseEntity<SecondDailyReport>) ResponseEntity.status(HttpStatus.valueOf("Not saved"));
         }

        }catch (Exception e){
            return (ResponseEntity<SecondDailyReport>) ResponseEntity.status(HttpStatus.valueOf("Not saved"));

        }



    }

    //////////////////////////// UPDATE REPORT /////////////////////////////////////


    public SecondDailyReport updateUserReport(Long id, SecondDailyReport dr) {
         SecondDailyReport dailyReports = dailyReportsRepo.findById(id).orElseThrow(() ->
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
