package com.fpt.projectthuviec.Company;



import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id ;
    @Column
    private  String name;
    @Column
    private  boolean active ;


}
