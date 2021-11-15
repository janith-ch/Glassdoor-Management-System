package com.app.systembackend.repository;

import com.app.systembackend.model.User;
import com.app.systembackend.model.dto.UserPermission;
import com.app.systembackend.repository.mapper.UserPermissionMapper;
import com.app.systembackend.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    @Qualifier("system-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User addUser(User user) {

        try {

            Map<String, Object> param = new HashMap<>();
            param.put("name", user.getName());
            param.put("userName", user.getUserName());
            param.put("password", user.getPassword());
            param.put("email", user.getEmail());
            param.put("userRole", user.getUserRole());
            param.put("city", user.getCity());
            param.put("contact", user.getContact());

            String query = "INSERT INTO `USER` (`id`,`name`, `user_name`, `password`,`email`,`user_role`,`city`,`contact`)" +
                    " VALUES (NULL,:name, :userName,:password,:email,:userRole,:city,:contact )";

            namedParameterJdbcTemplate.update(query, param);


        } catch (Exception e) {

            throw new RuntimeException("error getting user insert ==> " + e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {

        String query = "SELECT * \n" +
                "FROM user u\n" +
                " JOIN user_role ur\n" +
                "  ON u.role_id = ur.role_id\n";

        try {

            List<User> user = namedParameterJdbcTemplate.query(query,new UserRowMapper());
            return user;

        } catch (Exception e) {

            throw new RuntimeException("error getting reading users ==> " + e.getMessage());

        }
    }

    public List<UserPermission> getAllUserPermissions(int id){

        String query = "SELECT * \n" +
                "FROM role_permission rp\n" +
                "JOIN user_permission up\n" +
                "    ON rp.user_permission_id = up.permission_id\n" +
                " WHERE user_role_id =:id";

        return namedParameterJdbcTemplate.query(query, Collections.singletonMap("id",id),new UserPermissionMapper());

    }

    public User getUserById(int id){

        String query = "SELECT * \n" +
                "FROM user u\n" +
                " JOIN user_role ur\n" +
                "  ON u.role_id = ur.role_id\n" +
                "WHERE  u.id =:id";

        try {

            User user = namedParameterJdbcTemplate.queryForObject(query,Collections.singletonMap("id",id),new UserRowMapper());

            return user;

        } catch (Exception e) {

            throw new RuntimeException("error getting reading users ==> " + e.getMessage());

        }
    }
}
