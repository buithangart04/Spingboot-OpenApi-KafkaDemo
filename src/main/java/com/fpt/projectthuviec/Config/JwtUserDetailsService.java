package com.fpt.projectthuviec.Config;

import com.fpt.projectthuviec.Account.Account;
import com.fpt.projectthuviec.Account.AccountRepository;
import com.fpt.projectthuviec.Account.CustomAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository act;


 public  void save(Account account){
     act.save(account);

 }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = act.findByUsername(username);
        if (account != null) {
            return new  CustomAccountDetails(account);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
