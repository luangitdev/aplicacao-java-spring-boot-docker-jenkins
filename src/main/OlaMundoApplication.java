package com.example.olamundo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class OlaMundoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlaMundoApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "index"; // Retorna o template index.html
    }
}