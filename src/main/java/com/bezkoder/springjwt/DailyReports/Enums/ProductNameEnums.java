package com.bezkoder.springjwt.DailyReports.Enums;

import javax.persistence.*;

@Entity
@Table(name = "ProductNameEnum")
public class ProductNameEnums {

                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ProductNameEnum productNameEnum;

        public ProductNameEnums(){}

    public ProductNameEnums(ProductNameEnum productNameEnum){
            this.productNameEnum = productNameEnum;

    }

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ProductNameEnum getName() {
    return productNameEnum;
  }

  public void setName(ProductNameEnum productNameEnum) {
    this.productNameEnum = productNameEnum;
  }

}
