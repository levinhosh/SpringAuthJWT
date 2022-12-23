package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.SecondDailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecondDailyReportRepo extends JpaRepository<SecondDailyReport, Long> {
    @Query(value = "SELECT  * FROM daily WHERE user_id=:userId", nativeQuery = true)
    List<SecondDailyReport> findByUniqueConstraint(@Param("userId") Long userId);
}
