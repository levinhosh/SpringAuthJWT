package com.bezkoder.springjwt.DailyReports.Enums;


import javax.persistence.*;

@Entity
@Table(name = "DepartmentEnum")
public class DepartmentEnums {


                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 50)
        private DepartmentEnum departmentEnum;

        public DepartmentEnums(){}

    public DepartmentEnums(DepartmentEnum departmentEnum){
            this.departmentEnum = departmentEnum;

    }

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DepartmentEnum getName() {
    return departmentEnum;
  }

  public void setName(DepartmentEnum departmentEnum) {
    this.departmentEnum = departmentEnum;
  }


}
