package com.bezkoder.springjwt.DailyReports;

import com.bezkoder.springjwt.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;



@Entity
@Table(name="daily_reports")
@NoArgsConstructor
@AllArgsConstructor
public class DailyReports {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_report_id")
    private Long id;


    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;
//
//
//
//
//    @Column(name = "ticketId", nullable = true)
//    private String ticketId;
//
//
//
//
//    @Column(name = "timeTaken", nullable = false)
//    private String timeTaken;
//
//    @Column(name = "DepartmentName", nullable = false)
//    private Long Dept_FK;
//
////    @Column(name = "ClientName", nullable = false)
////    private Long Client_FK;
//
//    @Column(name = "ProductName", nullable = false)
//    private Long Prdt_FK;
//
//    @Column(name = "ReportCategory", nullable = false)
//    private Long Rept_FK;
//
//
//
//    @Column(name = "description", updatable = true, nullable = false)
//    private String report_description;
//
//
//
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;
}