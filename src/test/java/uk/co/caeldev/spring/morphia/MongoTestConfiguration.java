package uk.co.caeldev.spring.morphia;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import uk.co.caeldev.spring.moprhia.MongoSettings;

import java.io.IOException;

@Configuration
public class MongoTestConfiguration {
    @Bean
    @Profile("test")
    public MongoClient mongoClient(final MongoSettings mongoSettings) throws IOException {
        final Fongo fongo = new Fongo(mongoSettings.getDatabase());
        return fongo.getMongo();
    }
}
