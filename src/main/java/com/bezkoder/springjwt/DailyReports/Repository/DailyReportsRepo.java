package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DailyReportsRepo extends JpaRepository<DailyReports, Long> {


    @Query(value = "SELECT  * FROM daily_reports WHERE app_user_id=:userId", nativeQuery = true)
    List<DailyReports> findByUniqueConstraint(@Param("userId") Long userId);

}
