package com.bezkoder.springjwt.DailyReports.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ProductNameEnum")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNameEnums {

                @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

//        @Enumerated(EnumType.STRING)
//        @Column(length = 20)
//        private ProductNameEnum productNameEnum;

        @Column(length = 20)
        private String productName;

//        public ProductNameEnums(){}
//
//    public ProductNameEnums(ProductNameEnum productNameEnum){
//            this.productNameEnum = productNameEnum;
//
//    }

//    public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }

//  public ProductNameEnum getName() {
//    return productNameEnum;
//  }
//
//  public void setName(ProductNameEnum productNameEnum) {
//    this.productNameEnum = productNameEnum;
//  }

}
