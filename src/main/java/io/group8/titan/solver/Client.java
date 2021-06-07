package io.group8.titan.solver;

import io.group8.titan.space.SolarSystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;

public class Client {

    private static final String baseUrl = "http://localhost:8081";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void addSystem(SolarSystem solarSystem) {
        String url = baseUrl + "/SolarSystem";
        restTemplate.postForObject(url, solarSystem, String.class);
    }
}
