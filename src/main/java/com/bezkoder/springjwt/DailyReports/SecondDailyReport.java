package com.bezkoder.springjwt.DailyReports;


import com.bezkoder.springjwt.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="daily")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecondDailyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;

    @Column(nullable = true)
    private String ticketId;

     @Column(nullable = false)
    private String timeTaken;

    @Column(nullable = false)
    private Long Dept_FK;

    @Column(nullable = false)
    private Long Client_FK;

    @Column(nullable = false)
    private Long Prdt_FK;

    @Column(nullable = false)
    private Long Rept_FK;

    @Column(updatable = true, nullable = false)
    private String report_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
