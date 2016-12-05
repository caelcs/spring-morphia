package uk.co.caeldev.spring.moprhia;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MongoSettings.class)
@ConditionalOnProperty({
        "mongo.servers[0].name",
        "mongo.servers[0].port",
        "mongo.database",
        "mongo.username",
        "mongo.password"})
public class MongoConfiguration {

    @Bean
    public MongoServer mongoServer() {
        return new MongoServer();
    }

    @Bean
    public MongoSettings mongoSettings() {
        return new MongoSettings();
    }

}