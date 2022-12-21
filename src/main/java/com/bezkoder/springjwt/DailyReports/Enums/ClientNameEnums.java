package com.bezkoder.springjwt.DailyReports.Enums;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class ClientNameEnums {
            @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ClientNameEnum clientName;

        public ClientNameEnums(){}

    public ClientNameEnums(ClientNameEnum clientName){
            this.clientName = clientName;

    }

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ClientNameEnum getClientName() {
    return clientName;
  }

  public void setClientName(ClientNameEnum clientNameEnum) {
    this.clientName = clientName;
  }

}
