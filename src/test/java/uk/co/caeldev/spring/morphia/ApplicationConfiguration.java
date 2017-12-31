package uk.co.caeldev.spring.morphia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import uk.co.caeldev.spring.moprhia.EnableSpringMorphia;

@SpringBootConfiguration
@EnableSpringMorphia
public class ApplicationConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}