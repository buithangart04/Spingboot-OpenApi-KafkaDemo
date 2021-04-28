package com.fpt.projectthuviec.Account;

import com.fpt.projectthuviec.Config.JwtTokenUtil;
import com.fpt.projectthuviec.Config.JwtUserDetailsService;
import com.fpt.projectthuviec.Model.JwtRequest;
import com.fpt.projectthuviec.Model.JwtResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v3")
@Tag(name = "account")
public class AccountController {
    private static final String TOPIC = "my_topic1" ;
    @Autowired
    private   KafkaTemplate<String,JwtRequest> kafkaTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @PostMapping ( "/register")
    public void saveUser( @RequestBody  Account acount)  {
        // set account encode by bcrypt
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        acount.setPassword(encoder.encode(acount.getPassword()));
      userDetailsService.save(acount);
    }

    @PostMapping(value = "/authentication",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
           kafkaTemplate.send(TOPIC, authenticationRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
