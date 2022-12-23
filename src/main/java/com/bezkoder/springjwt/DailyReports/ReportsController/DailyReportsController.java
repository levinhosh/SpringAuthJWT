package com.bezkoder.springjwt.DailyReports.ReportsController;


import com.bezkoder.springjwt.DailyReports.DailyReportServices.ClientService;
import com.bezkoder.springjwt.DailyReports.DailyReportServices.DepartmentNameService;
import com.bezkoder.springjwt.DailyReports.DailyReportServices.ProductNameService;
import com.bezkoder.springjwt.DailyReports.DailyReportServices.ReportCategoryService;
import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnums;
import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnums;
import com.bezkoder.springjwt.DailyReports.Enums.ReportCategorys;
import com.bezkoder.springjwt.DailyReports.Repository.ClientNameEnumRepo;
import com.bezkoder.springjwt.DailyReports.Repository.DepartmentEnumRepo;
import com.bezkoder.springjwt.DailyReports.Repository.ProductNameEnumRepo;
import com.bezkoder.springjwt.DailyReports.Repository.ReportCategoryRepo;
import com.bezkoder.springjwt.DailyReports.SecondDailyReport;
import com.bezkoder.springjwt.DailyReports.Services.SecondDailyReportsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
@Slf4j
public class DailyReportsController {

    //private final UserService userService;

    @Autowired
    ClientNameEnumRepo clientNameEnumRepo;
    @Autowired
    DepartmentEnumRepo departmentEnumRepo;
    @Autowired
    ProductNameEnumRepo productNameEnumRepo;
    @Autowired
    ReportCategoryRepo reportCategoryRepo;

    @Autowired
    private SecondDailyReportsService dailyReportsService;

    @Autowired
    private ClientService clientService;


    @Autowired
    private DepartmentNameService departmentNameService;

    @Autowired
    private ProductNameService productNameService;

    @Autowired
    ReportCategoryService reportCategoryService;



    ////////////// VIEW ALL REPORTS /////////////////////////////////////
    @GetMapping(path = "/allreports") //Read all
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<SecondDailyReport> allReports()
    {
        return dailyReportsService.getAllReports();
    }


    ///////////////////////  CREATE REPORT /////////////////////////////
    @PostMapping(value="/create/{appUser}")
    public ResponseEntity<SecondDailyReport> createReport(@RequestBody SecondDailyReport dailyReports, @PathVariable Long appUser) {
        try {
            return dailyReportsService.createReport(appUser, dailyReports);

        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }
    }

    ////////////////////// UPDATE REPORT //////////////

    @RequestMapping(value="/updateReport/{id}", method = RequestMethod.PUT)  // Update by id
    public SecondDailyReport updateDailyReports(@PathVariable Long id, @RequestBody SecondDailyReport dailyReports) {

        return (dailyReportsService.updateUserReport(id,dailyReports));

    }

    /////////////////  SINGLE USER REPORT /////////////

    @GetMapping(value = "/report/{id}") //Read by single user records
    public @ResponseBody Optional<List<SecondDailyReport>> getUserByReport(@PathVariable Long id) {
        List<SecondDailyReport> dailyReports = dailyReportsService.readReport(id);
        return Optional.ofNullable(dailyReports);

    }

    //////////////  CLIENT NAME   ////////////

    @PostMapping(value="/createClient")
    public ClientNameEnums createClient(@RequestBody ClientNameEnums clientNameEnums) {
        try {
            return clientService.addClient(clientNameEnums);
        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }
    }


    @GetMapping(value = "/client/{id}") //Read by single user records
    public @ResponseBody Optional<Optional<ClientNameEnums>> getClient(@PathVariable Long id) {
        Optional<ClientNameEnums> clientNameEnums = clientService.getClientById(id);
        return Optional.ofNullable(clientNameEnums);

    }

    @GetMapping(value = "/allClients")
    public List<ClientNameEnums> getAllClients(){
        return clientService.getAllClients();
    }

    ////////////////// PRODUCTS NAME  ///////////////////////////////////


    @PostMapping(value="/createProductName")
    public ProductNameEnums createProductName(@RequestBody ProductNameEnums productNameEnums) {
        try {
            return productNameService.addProduct(productNameEnums);
        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }
    }


    @GetMapping(value = "/product/{id}") //Read by single user records
    public @ResponseBody Optional<Optional<ProductNameEnums>> getProductName(@PathVariable Long id) {
        Optional<ProductNameEnums> productNameEnums = productNameService.getProductById(id);
        return Optional.ofNullable(productNameEnums);

    }

    @GetMapping(value = "/allProductNames")
    public List<ProductNameEnums> getAllProductNames(){
        return productNameService.getAllProductNames();
    }

    //////////////////////// DEPARTMENT NAMES ////////////////////////////////////


    @PostMapping(value="/createDeptName")
    public DepartmentEnums createDepartmentName(@RequestBody DepartmentEnums departmentEnums) {
        try {
            return departmentNameService.addDepartment(departmentEnums);
        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }
    }


    @GetMapping(value = "/dept/{id}") //Read by single user records
    public @ResponseBody Optional<Optional<DepartmentEnums>> getDepartmentName(@PathVariable Long id) {
        Optional<DepartmentEnums> departmentEnums = departmentNameService.getDepartmentById(id);
        return Optional.ofNullable(departmentEnums);

    }

    @GetMapping(value = "/alldept")
    public List<DepartmentEnums> getAllDepartmentNames(){
        return departmentNameService.getAllDepartmentNames();
    }


    ///////////////////////////////// REPORT CATEGORY /////////////////////////


    @PostMapping(value="/createrptcategoryName")
    public ReportCategorys createReportCategoryName(@RequestBody ReportCategorys reportCategorys) {
        try {
            return reportCategoryService.addCategory(reportCategorys);
        }catch (Exception e){
            log.info("Error{} "+e);
            return null;
        }
    }


    @GetMapping(value = "/rptCategory/{id}") //Read by single user records
    public @ResponseBody Optional<Optional<ReportCategorys>> getRptCatName(@PathVariable Long id) {
        Optional<ReportCategorys> reportCategorys = reportCategoryService.getCategoryById(id);
        return Optional.ofNullable(reportCategorys);

    }

    @GetMapping(value = "/allrptCategory")
    public List<ReportCategorys> getAllRptCategoryNames(){
        return reportCategoryService.getAllCategoryNames();
    }

    /////////////////////////////////////////////////////////////////////


}
