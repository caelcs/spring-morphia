package uk.co.caeldev.spring.moprhia;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({MongoSettings.class})
@ConditionalOnProperty({"morphia.entityPackage"})
public class MorphiaConfiguration {

    @Bean
    public MorphiaSettings morphiaSettings() {
        return new MorphiaSettings();
    }

    @Bean
    public Morphia morphia(final MorphiaSettings morphiaSettings) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage(morphiaSettings.getEntityPackage());
        return morphia;
    }

    @Bean
    public Datastore dataStore(final Morphia morphia,
                               final MongoClient mongoClient,
                               final MongoSettings mongoSettings) {
        final Datastore datastore = morphia.createDatastore(mongoClient, mongoSettings.getDatabase());
        datastore.ensureIndexes();
        return datastore;
    }
}
