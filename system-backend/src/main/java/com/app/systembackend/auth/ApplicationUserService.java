package com.app.systembackend.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {


    @Autowired
    @Qualifier("user_dao")
    private ApplicationUserDao applicationUserDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return applicationUserDao.selectApplicationUserByUsername(username);

    }
}





