package com.app.systembackend.auth;

public interface ApplicationUserDao {

    ApplicationUser selectApplicationUserByUsername(String username);
}
