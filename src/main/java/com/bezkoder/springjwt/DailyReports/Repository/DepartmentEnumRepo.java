package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnum;
import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnums;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentEnumRepo  extends JpaRepository<DepartmentEnums, Long> {
    Optional<DepartmentEnums> findByDepartmentEnum(DepartmentEnum departmentEnum);
}
