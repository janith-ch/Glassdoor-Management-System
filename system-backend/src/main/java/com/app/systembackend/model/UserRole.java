package com.app.systembackend.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.List;



@Data
public class UserRole {

    @Id
    private int id;
    private String roleName;
    private List<UserPermission> userPermissions;
}
