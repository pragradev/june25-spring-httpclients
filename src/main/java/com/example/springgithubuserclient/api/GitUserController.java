package com.example.springgithubuserclient.api;

import com.example.springgithubuserclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/git")
public class GitUserController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public ResponseEntity<String> getUser(@RequestParam String username){
        return userService.getUserFromGithubFeignClient(username);
        // if i dont want to make DTO
    }
}
