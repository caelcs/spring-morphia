package uk.co.caeldev.spring.moprhia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

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

    @Bean
    @Profile("!test")
    public MongoClient mongoClient(final MongoSettings mongoSettings) throws Exception {
        final List<ServerAddress> serverAddresses = mongoSettings.getServers()
                .stream()
                .map((MongoServer input) -> new ServerAddress(input.getName(), input.getPort()))
                .collect(toList());

        final MongoCredential credential = MongoCredential.createCredential(
                mongoSettings.getUsername(),
                mongoSettings.getDatabase(),
                mongoSettings.getPassword().toCharArray());

        return new MongoClient(
                serverAddresses, newArrayList(credential));
    }
}