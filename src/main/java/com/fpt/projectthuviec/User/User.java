package com.fpt.projectthuviec.User;


import com.fpt.projectthuviec.Company.Company;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id ;
    @Column
    private String name ;
    @Column
    private boolean active ;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false,referencedColumnName = "id")
    private Company company;


}
