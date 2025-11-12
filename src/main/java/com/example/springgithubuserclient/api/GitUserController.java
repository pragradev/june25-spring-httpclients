package com.example.springgithubuserclient.api;

import com.example.springgithubuserclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@RestController
@RequestMapping("/git")
@Slf4j
public class GitUserController {
    @Autowired
    UserService userService;
    // logger singlton
    Logger logger = LoggerFactory.getLogger(GitUserController.class);

    @GetMapping("/get")
    public ResponseEntity<String> getUser(@RequestParam String username){
        // take snapshop time at starting
        // log statement : class: GitUserController method: getUser started execution
        // 2) slf4j, apache log4j
        //logger.info(Instant.now() + " Class: GitUserController method: getUser started execution with argument " + username );
        ResponseEntity<String> userFromGithubFeignClient = userService.getUserFromGithubFeignClient(username);
        //logger.info(Instant.now() + " Class: GitUserController method: getUser finished execution with return of " +  userFromGithubFeignClient);

        // log statement : class: GitUserController method: getUser finished execution
        // take snapshop time at finishing
        //
        return userFromGithubFeignClient;
        // if i dont want to make DTO
    }
}
