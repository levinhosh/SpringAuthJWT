package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientNameEnumRepo  extends JpaRepository<ClientNameEnums, Long> {



}


