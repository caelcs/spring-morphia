package uk.co.caeldev.spring.moprhia.repository;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.data.repository.CrudRepository;
import uk.co.caeldev.spring.moprhia.utils.Iterables;

import java.io.Serializable;

import static java.util.Objects.nonNull;

public class GenericMorphiaRepository<T, ID extends Serializable> implements CrudRepository<T, ID> {

    protected Class<T> entityClazz;

    private final Datastore datastore;

    public GenericMorphiaRepository(Class<T> clazz, final Datastore datastore) {
        this.datastore = datastore;
        this.entityClazz = clazz;
    }

    @Override
    public <S extends T> S save(S entity) {
        final Key<S> save = datastore.save(entity);
        return datastore.get(save.getType(), save.getId());
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        throw new UnsupportedOperationException();
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
