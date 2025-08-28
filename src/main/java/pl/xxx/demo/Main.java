package pl.xxx.demo;

import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/v1/taborets/{id}";
//        Taboret user = restTemplate.getForObject(url, Taboret.class, 2);
//        System.out.println(user);

        String url2 = "https://dummyjson.com/products/{id}";
        Product forObject = restTemplate.getForObject(url2, Product.class, 1);
        System.out.println(forObject);

    }
}