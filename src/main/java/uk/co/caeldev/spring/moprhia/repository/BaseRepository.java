package uk.co.caeldev.spring.moprhia.repository;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public class BaseRepository<T, ID extends Serializable> implements CrudRepository<T, ID> {

    private final Datastore datastore;

    public BaseRepository(final Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public <S extends T> S save(S entity) {
        final Key<S> result = datastore.save(entity);
        return null;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public T findOne(ID id) {
        return null;
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void delete(Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
