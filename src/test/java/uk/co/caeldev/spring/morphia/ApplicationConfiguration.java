package uk.co.caeldev.spring.morphia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import uk.co.caeldev.spring.moprhia.EnableSpringMorphia;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableSpringMorphia
public class ApplicationConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}