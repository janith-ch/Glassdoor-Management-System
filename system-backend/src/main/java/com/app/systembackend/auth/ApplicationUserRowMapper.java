package com.app.systembackend.auth;

import com.app.systembackend.model.User;
import com.app.systembackend.model.dto.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationUserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt("role_id"));
        userRole.setRoleName(resultSet.getString("role"));

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setUserName(resultSet.getString("user_name"));
        user.setEmail(resultSet.getString("email"));
        user.setUserRole(userRole);
        user.setPassword(resultSet.getString("password"));
        user.setCity(resultSet.getString("city"));
        user.setContact(resultSet.getInt("contact"));

        return user;
    }
}

