package uk.co.caeldev.spring.moprhia.repository;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.data.repository.CrudRepository;
import uk.co.caeldev.spring.moprhia.utils.Iterables;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;

public class BaseMorphiaRepository<T, ID extends Serializable> implements CrudRepository<T, ID> {

    protected Class<T> entityClazz;

    private final Datastore datastore;

    public BaseMorphiaRepository(final Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public <S extends T> S save(S entity) {
        final Key<S> result = datastore.save(entity);
        return (S)result.getId();
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        final Iterable<Key<S>> result = datastore.save(entities);
        final List<S> converted = StreamSupport
                .stream(result.spliterator(), false)
                .map(iter -> (S)iter.getId())
                .collect(Collectors.toList());
        return Iterables.from(converted);
    }

    @Override
    public T findOne(ID id) {
        return datastore.get(getEntityClass(), id);
    }

    @Override
    public boolean exists(ID id) {
        return nonNull(datastore.get(getEntityClass(), id));
    }

    @Override
    public Iterable<T> findAll() {
        return Iterables.from(datastore.find(getEntityClass()).asList());
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return datastore.get(getEntityClass(), ids);
    }

    @Override
    public long count() {
        return datastore.getCount(getEntityClass());
    }

    @Override
    public void delete(ID id) {
        datastore.delete(getEntityClass(), id);
    }

    @Override
    public void delete(T entity) {
        datastore.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        datastore.delete(datastore.createQuery(getEntityClass()));
    }

    public Class<T> getEntityClass() {
        return entityClazz;
    }
}
