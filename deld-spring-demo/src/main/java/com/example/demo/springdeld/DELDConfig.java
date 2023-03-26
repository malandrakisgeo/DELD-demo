package com.example.demo.springdeld;

import org.gmalandrakis.deld.core.DELDBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DELDConfig {
    @Bean
    public TestService transferService() {
        return (TestService)  new DELDBuilder().setBaseURL("http://localhost:8080/test/").forService(TestService.class);
    }
}