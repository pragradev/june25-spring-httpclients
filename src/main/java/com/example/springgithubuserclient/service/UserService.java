package com.example.springgithubuserclient.service;

import com.example.springgithubuserclient.feignClients.GithubClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@Slf4j
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;
    Logger logger = LoggerFactory.getLogger(UserService.class);

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
//        logger.info(Instant.now() + " Class: UserService method: getUserFromGithubFeignClient started execution with argument " + username );
        String responseGitUser = feignGithubClient.fetchUserFromGithub(username);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().header("company","CIBC").body(responseGitUser);

//        logger.info(Instant.now() + " Class: UserService method: getUserFromGithubFeignClient finished execution with argument " + responseEntity );

        //rest template
        return responseEntity;
    }
}
