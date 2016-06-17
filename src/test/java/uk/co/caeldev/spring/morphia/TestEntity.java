package uk.co.caeldev.spring.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("TestEntity")
public class TestEntity {
    @Id
    private ObjectId id;
    private String name;

    public TestEntity() {
    }

    public TestEntity(String name) {
        this.name = name;
    }

    public TestEntity(final ObjectId id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObjectId getId() {
        return id;
    }
}
