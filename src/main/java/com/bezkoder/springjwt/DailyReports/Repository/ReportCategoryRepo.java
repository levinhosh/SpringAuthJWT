package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.ReportCategorys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCategoryRepo  extends JpaRepository<ReportCategorys, Long> {

}


