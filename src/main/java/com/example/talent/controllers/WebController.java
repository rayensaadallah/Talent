package com.example.talent.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/scrape")
@CrossOrigin("*")
public class WebController {
    public final RestTemplate restTemplate;

    public WebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/flask-data")
    public ResponseEntity<String> getDataFromFlask() {
        String flaskUrl = "http://127.0.0.1:5000/display_skills"; // Adjust the URL as needed
        ResponseEntity<String> response = restTemplate.getForEntity(flaskUrl, String.class);
        return response;
    }
}
