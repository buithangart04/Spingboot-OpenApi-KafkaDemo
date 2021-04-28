package com.fpt.projectthuviec.User;

import com.fpt.projectthuviec.Company.Company;
import com.fpt.projectthuviec.Company.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "user")

public class UserController {

    @Autowired
    private UserService userService;
//get user by it's company
    @GetMapping("/company/{comId}/users")
    @Operation(summary = "get user by it's company",security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getUserByCompany( @PathVariable long  comId) {
        return userService.loadUserByCompany(comId);
    }
//get all users
    @GetMapping("/users")
    @Operation(summary = "get all users",security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get user by id
    @GetMapping("/users/{id}")
    @Operation(summary = "get user by id ",security = @SecurityRequirement(name = "bearerAuth"))
    public User getUser( @PathVariable long  id ) {
        return userService.getUserById(id);
    }

    //add a user
    @PostMapping(value = "users",consumes = "application/json",produces = "application/json")
    @Operation(summary = "add a user",security = @SecurityRequirement(name = "bearerAuth"))
    public void addUser( @RequestBody User user ){
        userService.addUser(user);
    }

    //update a user
    @PutMapping(value = "/user/{id}",consumes = "application/json",produces = "application/json")
    @Operation(summary = "update a user",security = @SecurityRequirement(name = "bearerAuth"))
    public void updateUser( @RequestBody User user  ) {
        userService.updateUser(user);
    }
}
