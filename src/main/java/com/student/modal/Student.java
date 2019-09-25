package com.student.modal;

import lombok.Data;
import org.springframework.stereotype.Component;


import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sno;
    @Column(name = "student_name")
    private String sname;
    @Column(name = "english")
    private int english;
    @Column(name = "maths")
    private int maths;
    @Column(name = "science")
    private int science;
    @Column(name = "total")
    private int total;
    @Column(name = "average")
    private double average;
    private String status;


}
