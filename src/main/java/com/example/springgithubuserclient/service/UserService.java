package com.example.springgithubuserclient.service;

import com.example.springgithubuserclient.feignClients.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;

    @Autowired
    GithubClient feignGithubClient;

    @Value("${giturlhost}")
    String githubHost;
    @Value("${giturlendpoint}")
    String getEndPointGithub;

    public ResponseEntity<String> getUserFromGithub(String username){
        ResponseEntity<String> userData = restTemplate.getForEntity(githubHost+ getEndPointGithub + username, String.class);
        //rest template
        return userData;
    }

    public ResponseEntity<String> getUserFromGithubWebClient(String username){
        String responseGitUser = webClient
                .get()
                .uri(githubHost+ getEndPointGithub + username)
                .header("company","pragra")
                .header("location","Mississauga")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(900))
                .block();
        ResponseEntity<String> responseEntity = ResponseEntity.ok().header("company","CIBC").body(responseGitUser);

        //rest template
        return responseEntity;
    }

    public ResponseEntity<String> getUserFromGithubFeignClient(String username){
        String responseGitUser = feignGithubClient.fetchUserFromGithub(username);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().header("company","CIBC").body(responseGitUser);

        //rest template
        return responseEntity;
    }
}
