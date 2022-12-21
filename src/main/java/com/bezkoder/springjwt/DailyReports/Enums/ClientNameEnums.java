package com.bezkoder.springjwt.DailyReports.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientNameEnums {
            @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            @Column(length = 50)
            private String clientName;
}
