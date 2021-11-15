package com.app.systembackend.model.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;


import java.util.List;
import java.util.Set;


@Data
public class UserRole {

    @Id
    private int id;
    private String roleName;
    private List<UserPermission> userPermissions;
}
