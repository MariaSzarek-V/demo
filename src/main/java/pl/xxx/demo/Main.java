package pl.xxx.demo;

import org.springframework.web.client.RestTemplate;
import pl.xxx.demo.User.User;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/{id}";
        User user = restTemplate.getForObject(url, User.class, 2);
        System.out.println(user);


    }
}