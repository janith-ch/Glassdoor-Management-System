package com.app.systembackend.repository;

import com.app.systembackend.constant.CommonConstant;
import com.app.systembackend.constant.UserConstant;
import com.app.systembackend.model.User;
import com.app.systembackend.model.UserPermission;
import com.app.systembackend.repository.mapper.UserPermissionMapper;
import com.app.systembackend.repository.mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserRepository {


    @Qualifier("system-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private UserConstant userConstant;
    private CommonConstant commonConstant;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          UserConstant userConstant,
                          CommonConstant commonConstant) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userConstant = userConstant;
        this.commonConstant = commonConstant;
    }

    public boolean addUser(User user) {

        try {

            Map<String, Object> param = new HashMap<>();
            param.put(userConstant.getNAME(), user.getName());
            param.put(userConstant.getUSERNAME(), user.getUserName());
            param.put(userConstant.getPASSWORD(), user.getPassword());
            param.put(userConstant.getEmail(), user.getEmail());
            param.put(userConstant.getROLE_ID(), user.getUserRole().getId());
            param.put(userConstant.getOCCUPATION(), user.getOccupation());
            param.put(userConstant.getCONTACT(), user.getContact());

            String query = userConstant.getUSER_INSERT_QUERY();

            int result = namedParameterJdbcTemplate.update(query, param);

            if (result == commonConstant.getONE()) {
                log.info("User registration Successful {} ", user);

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            log.info("Error in user registration {} ", user, e.getMessage());

            throw new RuntimeException("Error getting user insert ==> " + e.getMessage());

        }

    }

    public List<User> getAllUsers() {

        String query = "SELECT * \n" +
                "FROM user u\n" +
                " JOIN user_role ur\n" +
                "  ON u.role_id = ur.role_id\n";

        try {

            List<User> user = namedParameterJdbcTemplate.query(query, new UserRowMapper());
            return user;

        } catch (Exception e) {

            throw new RuntimeException("error getting reading users ==> " + e.getMessage());

        }
    }

    public List<UserPermission> getAllUserPermissions(int id) {

        String query = "SELECT * \n" +
                "FROM role_permission rp\n" +
                "JOIN user_permission up\n" +
                "    ON rp.user_permission_id = up.permission_id\n" +
                " WHERE user_role_id =:id";

        return namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new UserPermissionMapper());

    }

    public User getUserById(int id) {

        String query = "SELECT * \n" +
                "FROM user u\n" +
                " JOIN user_role ur\n" +
                "  ON u.role_id = ur.role_id\n" +
                "WHERE  u.id =:id";

        try {

            User user = namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("id", id), new UserRowMapper());

            return user;

        } catch (Exception e) {

            throw new RuntimeException("error getting reading users ==> " + e.getMessage());

        }
    }
}
