package com.app.systembackend.auth;


import com.app.systembackend.model.User;
import com.app.systembackend.model.UserPermission;
import com.app.systembackend.repository.mapper.UserPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("user_dao")
public class ApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public ApplicationUser selectApplicationUserByUsername(String username) {


        try {

            String query = "SELECT * \n" +
                    "FROM user u\n" +
                    " JOIN user_role ur\n" +
                    "  ON u.role_id = ur.role_id\n" +
                    "WHERE  u.user_name =:username";

            User user = namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("username", username), new ApplicationUserRowMapper());

            List<UserPermission> permissions = getAllUserPermissions(user.getUserRole().getId());

            user.getUserRole().setUserPermissions(permissions);

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = getGrantedAuthorities(permissions, user.getUserRole().getRoleName());

            ApplicationUser applicationUser = new ApplicationUser(
                    user.getId(),
                    user.getUserName(),
                    passwordEncoder.encode(user.getPassword()),
                    simpleGrantedAuthorities,
                    true,
                    true,
                    true,
                    true
            );
            return applicationUser;

        } catch (Exception e) {

            throw new RuntimeException("error getting read user  " + e.getMessage());
        }

    }

    List<UserPermission> getAllUserPermissions(int id) {

        String query = "SELECT * \n" +
                "FROM role_permission rp\n" +
                "JOIN user_permission up\n" +
                "    ON rp.user_permission_id = up.permission_id\n" +
                " WHERE user_role_id =:id";

        return namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new UserPermissionMapper());

    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities(List<UserPermission> getPermissions, String role) {

        List<SimpleGrantedAuthority> permissions = getPermissions.stream()

                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))

                .collect(Collectors.toList());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + role));

        return permissions;
    }


}
