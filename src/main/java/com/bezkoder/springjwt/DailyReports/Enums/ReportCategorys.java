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



        @Column(length = 20)
        private String reportCategory;



}
