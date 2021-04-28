package com.fpt.projectthuviec.Company;


import com.fpt.projectthuviec.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "company")

public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
  //get all companies
    @GetMapping("/companies")
    @Operation(summary = "get all companies",security = @SecurityRequirement(name = "bearerAuth"))
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
    //get company by id
    @GetMapping("/company/{id}")
    @Operation(summary = "get company by id ",security = @SecurityRequirement(name = "bearerAuth"))
    public Company getCompany( @PathVariable long  id ) {
        return companyService.getCompanyById(id);
    }
    //add a company
    @PreAuthorize("hasRole('admin')")
    @PostMapping(value = "companies",consumes = "application/json",produces = "application/json")
    @Operation(summary = "add a company  ",security = @SecurityRequirement(name = "bearerAuth"))
    public void addCompany( @RequestBody Company company ){
        companyService.addCompany(company);
    }
    //update a company
    @PreAuthorize("hasRole('admin')")
    @PutMapping(value = "/companies/{id}",consumes = "application/json",produces = "application/json")
    @Operation(summary = "update a company  ",security = @SecurityRequirement(name = "bearerAuth"))
    public void updateCompany( @RequestBody Company company , @PathVariable long  id ) {
        companyService.updateCompany(company);
        // deactive company then it will deactive the  user belong to this company
        if(company.isActive()==false){
        userService.DeactiveUserByCompany(company.getId());
        }
    }


}
