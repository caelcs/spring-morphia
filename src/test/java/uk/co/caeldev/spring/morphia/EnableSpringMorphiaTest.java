package uk.co.caeldev.spring.morphia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationConfiguration.class, MongoTestConfiguration.class}, initializers = {ConfigFileApplicationContextInitializer.class})
@ActiveProfiles("test")
public class EnableSpringMorphiaTest {
    
    @Autowired
    private Datastore datastore;
    
    @Test
    public void shouldPersistEntity() throws Exception {
        //Given
        final String name = string().next();
        final TestEntity testEntity = new TestEntity(null, name);

        //When
        final Key<TestEntity> save = datastore.save(testEntity);

        //Then
        final TestEntity result = datastore.get(TestEntity.class, save.getId());
        assertThat(result).isNotNull();
        assertThat(save.getId()).isEqualTo(result.getId());
        assertThat(name).isEqualTo(result.getName());
    }
}
