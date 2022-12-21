package com.bezkoder.springjwt.DailyReports.Request;

import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnums;
import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnums;
import com.bezkoder.springjwt.DailyReports.Enums.ReportCategorys;
import com.bezkoder.springjwt.models.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;



@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NewReportRequest {


    private Date creationDate;

    private final String ticketId;

    private final String timeTaken;

    private Long DepartmentName_id;

    private Long ClientName_id;

    private Long ProductName_id;

    private Long ReportCategory_id;

    private final String report_description;

    User user;
}
