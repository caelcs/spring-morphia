package uk.co.caeldev.spring.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static uk.org.fyodor.generators.RDG.string;

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

    private TestEntity(Builder builder) {
        name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public ObjectId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    public static final class Builder {
        private String name = string().next();

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public TestEntity build() {
            return new TestEntity(this);
        }
    }
}
