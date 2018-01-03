package uk.co.caeldev.spring.moprhia.repository;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.spring.morphia.ApplicationConfiguration;
import uk.co.caeldev.spring.morphia.MongoTestConfiguration;
import uk.co.caeldev.spring.morphia.TestEntity;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfiguration.class, MongoTestConfiguration.class})
@ActiveProfiles("test")
public class GenericMorphiaRepositoryIntegrationTest {

    @Autowired
    private Datastore datastore;
    private GenericMorphiaRepository<TestEntity, ObjectId> testEntityRepository;

    @Before
    public void testee() throws Exception {
        testEntityRepository = new GenericMorphiaRepository<>(TestEntity.class, datastore);
    }

    @Before
    public void resetFongo() {
        final Set<String> collectionNames = datastore.getDB().getCollectionNames();
        collectionNames
                .stream()
                .forEach(iter -> datastore.getDB().getCollection(iter).drop());
    }

    @Test
    public void shouldSaveEntity() {
        //Given
        final TestEntity entity = TestEntity.newBuilder().build();

        //When
        final TestEntity result = this.testEntityRepository.save(entity);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(entity.getName());
    }

    @Test
    public void shouldFindOne() {
        //Given
        final TestEntity testEntityToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity testEntitySaved = this.testEntityRepository.save(testEntityToBeSave);

        //When
        final TestEntity result = this.testEntityRepository.findOne(testEntitySaved.getId());

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(testEntitySaved);
    }

    @Test
    public void shouldExistsWhenEntityIsSaved() {
        //Given
        final TestEntity testEntityToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity testEntitySaved = this.testEntityRepository.save(testEntityToBeSave);

        //When
        final boolean exists = this.testEntityRepository.exists(testEntitySaved.getId());

        //Then
        assertThat(exists).isTrue();
    }

    @Test
    public void shouldFindAll() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity testEntity1Saved = this.testEntityRepository.save(testEntity1ToBeSave);
        final TestEntity testEntity2Saved = this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        final Iterable<TestEntity> all = this.testEntityRepository.findAll();

        //Then
        assertThat(all).hasSize(2);
    }

    @Test
    public void shouldFindAllByIds() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity testEntity1Saved = this.testEntityRepository.save(testEntity1ToBeSave);
        final TestEntity testEntity2Saved = this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        final Iterable<TestEntity> all = this.testEntityRepository.findAll(newArrayList(testEntity1Saved.getId(),
                testEntity2Saved.getId()));

        //Then
        assertThat(all).hasSize(2);
    }

    @Test
    public void shouldCount() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        this.testEntityRepository.save(testEntity1ToBeSave);
        this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        final long count = this.testEntityRepository.count();

        //Then
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void shouldDeleteById() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity saved = this.testEntityRepository.save(testEntity1ToBeSave);
        this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        this.testEntityRepository.delete(saved.getId());
        final TestEntity result = testEntityRepository.findOne(saved.getId());

        //Then
        assertThat(result).isNull();
    }

    @Test
    public void shouldDeleteByEntity() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        final TestEntity saved = this.testEntityRepository.save(testEntity1ToBeSave);
        this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        this.testEntityRepository.delete(saved);
        final TestEntity result = testEntityRepository.findOne(saved.getId());

        //Then
        assertThat(result).isNull();
    }

    @Test
    public void shouldDeleteAll() {
        //Given
        final TestEntity testEntity1ToBeSave = TestEntity.newBuilder().build();
        final TestEntity testEntity2ToBeSave = TestEntity.newBuilder().build();

        //And
        this.testEntityRepository.save(testEntity1ToBeSave);
        this.testEntityRepository.save(testEntity2ToBeSave);

        //When
        this.testEntityRepository.deleteAll();
        final long count = testEntityRepository.count();

        //Then
        assertThat(count).isEqualTo(0);
    }
}