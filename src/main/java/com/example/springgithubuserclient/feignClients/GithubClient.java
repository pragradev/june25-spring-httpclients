package com.example.springgithubuserclient.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GithubClient", url = "https://api.github.com")
public interface GithubClient {

    @GetMapping("/users/{username}")
    String fetchUserFromGithub(@PathVariable("username") String username);
}
