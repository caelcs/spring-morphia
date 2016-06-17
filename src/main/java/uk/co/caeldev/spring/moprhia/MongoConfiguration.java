package uk.co.caeldev.spring.moprhia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableConfigurationProperties(MongoSettings.class)
@ConditionalOnProperty({
        "mongo.host",
        "mongo.port",
        "mongo.database",
        "mongo.username",
        "mongo.password"})
public class MongoConfiguration {

    @Bean
    public MongoSettings mongoSettings() {
        return new MongoSettings();
    }

    @Bean
    @Profile("!test")
    public MongoClient mongoClient(final MongoSettings mongoSettings) throws Exception {
        final ServerAddress serverAddress = new ServerAddress(
                mongoSettings.getHost(), mongoSettings.getPort());

        final MongoCredential credential = MongoCredential.createCredential(
                mongoSettings.getUsername(),
                mongoSettings.getDatabase(),
                mongoSettings.getPassword().toCharArray());

        return new MongoClient(
                serverAddress, newArrayList(credential));
    }
}