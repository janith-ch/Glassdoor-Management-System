package com.app.systembackend.model;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class User {

    @Id
    private int id;
    private String name;
    private String userName;
    private UserRole userRole;
    private String email;
    private String password;
    private Number contact;
    private String occupation;

}
