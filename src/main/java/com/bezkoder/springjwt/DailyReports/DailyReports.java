package com.bezkoder.springjwt.DailyReports;

import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import com.bezkoder.springjwt.DailyReports.Enums.DepartmentEnum;
import com.bezkoder.springjwt.DailyReports.Enums.ProductNameEnum;
import com.bezkoder.springjwt.DailyReports.Enums.ReportCategory;
import com.bezkoder.springjwt.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name="daily_reports")
@NoArgsConstructor
@AllArgsConstructor
public class DailyReports {


    @SequenceGenerator(
            name = "daily_report_sequence",
            sequenceName = "daily_report_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "daily_report_sequence"
    )


    @Column(name = "daily_report_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;

//    @Column(name = "department", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private DepartmentEnum departmentEnum;



//        @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(  name = "department_enum",
//        joinColumns = @JoinColumn(name = "daily_report_id"),
//        inverseJoinColumns = @JoinColumn(name = "department_enum_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<DepartmentEnum> departmentEnum = new HashSet<>();

//    @Column(name = "category", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private ReportCategory reportCategory;



//    @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(  name = "reportcategorynames",
//        joinColumns = @JoinColumn(name = "daily_report_id"),
//        inverseJoinColumns = @JoinColumn(name = "reportcategory_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<ReportCategory> reportCategories = new HashSet<>();


    @Getter
    @Setter
    @Column(name = "ticketId", nullable = true)
    private String ticketId;

    @Getter
    @Setter
    @Column(name = "timeTaken", nullable = false)
    private String timeTaken;


    @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "clientnames",
        joinColumns = @JoinColumn(name = "daily_report_id"),
        inverseJoinColumns = @JoinColumn(name = "client_enum_id"))
    private Set<ClientNameEnums> clientNameEnums = new HashSet<>();

//    @Column(name = "product_name", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private ProductNameEnum productNameEnum;



//    @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(  name = "productnames",
//        joinColumns = @JoinColumn(name = "daily_report_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_enum_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<ProductNameEnum> productName = new HashSet<>();


    @Getter
    @Setter
    @Column(name = "description", updatable = true, nullable = false)
    private String report_description;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;
}