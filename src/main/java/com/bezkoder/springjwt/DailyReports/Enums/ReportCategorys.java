package com.bezkoder.springjwt.DailyReports.Enums;


import javax.persistence.*;

@Entity
@Table(name = "RptCategorys")
public class ReportCategorys {


                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ReportCategory reportCategory;

        public ReportCategorys(){}

    public ReportCategorys(ReportCategory reportCategory){
            this.reportCategory = reportCategory;

    }

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ReportCategory getName() {
    return reportCategory;
  }

  public void setName(ReportCategory reportCategory) {
    this.reportCategory = reportCategory;
  }

}
