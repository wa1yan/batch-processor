package dev.waiyanhtet.batch_processor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class GreetingController {

    @GetMapping(value = "/greet")
    public ResponseEntity<?> greet() {
        return ResponseEntity.ok("Hello from backend service.");
    }
}
