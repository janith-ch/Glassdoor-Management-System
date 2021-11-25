package com.app.systembackend.constant;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserConstant {

    private String USER_INSERT_QUERY = "INSERT INTO `USER` (`id`,`name`, `user_name`, `password`,`email`,`role_id`,`occupation`,`contact`)" +
            " VALUES (NULL,:name, :userName,:password,:email,:roleId,:occupation,:contact )";

    private String NAME = "name";
    private String USERNAME = "userName";
    private String PASSWORD = "password";
    private String Email = "email";
    private String CONTACT = "contact";
    private String ROLE_ID = "roleId";
    private String OCCUPATION = "occupation";


}
