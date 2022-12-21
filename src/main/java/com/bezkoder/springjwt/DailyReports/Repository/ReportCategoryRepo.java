package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.ReportCategory;
import com.bezkoder.springjwt.DailyReports.Enums.ReportCategorys;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ReportCategoryRepo  extends JpaRepository<ReportCategorys, Long> {
    Optional<ReportCategorys> findByReportCategory(ReportCategory reportCategory);
}


