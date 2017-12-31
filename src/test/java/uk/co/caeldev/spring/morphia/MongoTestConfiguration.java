package uk.co.caeldev.spring.morphia;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caeldev.spring.moprhia.MongoSettings;

import java.io.IOException;

@TestConfiguration
public class MongoTestConfiguration {

    @Bean
    public MongoClient mongoClient(final MongoSettings mongoSettings) throws IOException {
        final Fongo fongo = new Fongo(mongoSettings.getDatabase());
        return fongo.getMongo();
    }
}
