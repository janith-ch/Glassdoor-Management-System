package com.app.systembackend.controller;
import com.app.systembackend.controller.response.CommonResponse;
import com.app.systembackend.model.User;
import com.app.systembackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public ResponseEntity<?>  addUser( @RequestBody User user) {
       log.info("received user request body {} " , user);
        boolean result = userService.addUser(user);

        if(result == true){

            return ResponseEntity.ok(new CommonResponse<Boolean>(null,true,"user registration successfully"));

        }else {

            return ResponseEntity.ok(new CommonResponse<Boolean>(null,false,"user registration failed"));
        }

    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }


}
