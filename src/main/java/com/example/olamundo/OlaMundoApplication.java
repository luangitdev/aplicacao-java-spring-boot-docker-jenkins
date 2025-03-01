package com.example.olamundo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@Controller
public class OlaMundoApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(OlaMundoApplication.class);

    public static void main(String[] args) {
        logger.info("Iniciando via main...");
        SpringApplication.run(OlaMundoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("Configurando Spring Boot para Tomcat externo...");
        return builder.sources(OlaMundoApplication.class);
    }

    @GetMapping("/")
    public String home() {
        logger.info("Endpoint / acessado");
        return "index";
    }
}