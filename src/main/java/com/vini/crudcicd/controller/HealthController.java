package com.vini.crudcicd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> HealthCheck(){
        return ResponseEntity.ok("CI CD Check");
    }
}
