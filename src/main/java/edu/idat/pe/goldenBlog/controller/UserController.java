package edu.idat.pe.goldenBlog.controller;

import edu.idat.pe.goldenBlog.exception.UserException;
import edu.idat.pe.goldenBlog.model.User;
import edu.idat.pe.goldenBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody User newUser){
        try{
            userService.createUser(newUser);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        }catch (UserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}

