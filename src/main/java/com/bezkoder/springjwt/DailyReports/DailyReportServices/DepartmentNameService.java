package com.bezkoder.springjwt.DailyReports.DailyReportServices;


import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnums;
import com.bezkoder.springjwt.DailyReports.Repository.DepartmentEnumRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentNameService {


    @Autowired
    private DepartmentEnumRepo departmentEnumRepo;

    private DepartmentEnums departmentEnums;

    public List<DepartmentEnums> getAllDepartmentNames() {

        try {
            return departmentEnumRepo.findAll();
        } catch (Exception e) {
            log.info("Error {} " + e);
            return null;
        }
    }

    public Optional<DepartmentEnums> getDepartmentById(Long id) {

        try {
            return Optional.ofNullable(departmentEnumRepo.findById(id).orElse(null));
        } catch (Exception e) {
            log.info("Error {} " + e);
            return null;
        }
    }

    public DepartmentEnums addDepartment(DepartmentEnums departmentEnums) {
        try {
            departmentEnums.setDepartmentName(departmentEnums.getDepartmentName());
            //clientNameEnums.setClientName(String.valueOf(clientNameEnums));
            DepartmentEnums newDeptNameEnums= departmentEnumRepo.save(departmentEnums);
            return newDeptNameEnums;
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }
}
