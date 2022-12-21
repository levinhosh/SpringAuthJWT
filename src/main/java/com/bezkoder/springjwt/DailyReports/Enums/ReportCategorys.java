package com.bezkoder.springjwt.DailyReports.Enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RptCategorys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportCategorys {


                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

//        @Enumerated(EnumType.STRING)
//        @Column(length = 20)
//        private ReportCategory reportCategory;


        @Column(length = 20)
        private String reportCategory;

//        public ReportCategorys(){}
//
//    public ReportCategorys(ReportCategory reportCategory){
//            this.reportCategory = reportCategory;
//
//    }

//    public Long getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }

//  public ReportCategory getName() {
//    return reportCategory;
//  }
//
//  public void setName(ReportCategory reportCategory) {
//    this.reportCategory = reportCategory;
//  }

}
