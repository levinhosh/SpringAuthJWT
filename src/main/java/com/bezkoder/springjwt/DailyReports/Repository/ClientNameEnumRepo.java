package com.bezkoder.springjwt.DailyReports.Repository;

import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnum;
import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ClientNameEnumRepo  extends JpaRepository<ClientNameEnums, Integer> {

    Optional<ClientNameEnums> findByClientName(ClientNameEnum clientName);
}


