package com.bezkoder.springjwt.DailyReports.Enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DepartmentEnum")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEnums {


                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

//        @Enumerated(EnumType.STRING)
//        @Column(length = 50)
//        private DepartmentEnum departmentEnum;

        @Column(length = 50)
        private String departmentName;

//        public DepartmentEnums(){}
//
//    public DepartmentEnums(DepartmentEnum departmentEnum){
//            this.departmentEnum = departmentEnum;
//
//    }

//    public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//  public DepartmentEnum getName() {
//    return departmentEnum;
//  }
//
//  public void setName(DepartmentEnum departmentEnum) {
//    this.departmentEnum = departmentEnum;
//  }


}
