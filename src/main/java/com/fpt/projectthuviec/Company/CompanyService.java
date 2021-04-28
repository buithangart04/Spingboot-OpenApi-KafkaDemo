package com.fpt.projectthuviec.Company;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    // getAllCompany
    public List<Company> getAllCompanies() {

        List<Company> companies= new ArrayList<>();
        companyRepository.findAll()
                .forEach(companies::add);
        return companies;
    }
// get company by id
    public Company getCompanyById(long id) {

        return companyRepository.getOne( id);
    }
// add company to db
    public void addCompany(Company company) {
        //topics.add(topic);
        companyRepository.save(company);
    }
 public void updateCompany(Company company){
        companyRepository.save(company);
 }
}
