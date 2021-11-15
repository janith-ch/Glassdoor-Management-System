package com.app.systembackend.model.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserPermission {

    @Id
    private int id;
    private String permissionName;

}
