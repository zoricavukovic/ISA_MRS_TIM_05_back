package com.example.BookingAppTeam05;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class Config {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String pingUrl = "https://isa-mrs-tim-05-back.herokuapp.com/";

    @Scheduled(fixedDelay = 30*60*1000)
    public void keepAwake(){
        String result = restTemplate.getForObject(pingUrl, String.class);
        System.out.println(result);
    }
}
