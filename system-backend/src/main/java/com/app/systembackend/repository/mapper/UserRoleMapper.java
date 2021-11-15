package com.app.systembackend.repository.mapper;

import com.app.systembackend.model.dto.UserPermission;
import com.app.systembackend.model.dto.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRoleMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {

        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt("role_id"));
        userRole.setRoleName(resultSet.getString("role"));


        return userRole;
    }
}
