package uk.co.caeldev.spring.morphia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import uk.co.caeldev.spring.moprhia.EnableSpringMorphia;

@Configuration
@EnableAutoConfiguration
@EnableSpringMorphia
@ActiveProfiles("test")
public class ApplicationConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}