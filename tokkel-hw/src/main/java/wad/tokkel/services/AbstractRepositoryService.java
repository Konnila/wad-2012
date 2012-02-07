package wad.tokkel.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AbstractRepositoryService<T> implements RepositoryService<T> {

    protected JpaRepository<T, Integer> repository;

    @Override
    public JpaRepository<T, Integer> getRepository() {
        return repository;
    }

    public void setRepository(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public Iterable<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public Page<T> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public T findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public boolean exists(Integer id) {
        return repository.exists(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true,
    rollbackFor = Throwable.class)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public Iterable<T> save(Iterable<? extends T> itrbl) {
        return repository.save(itrbl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public T saveAndFlush(T t) {
        return repository.saveAndFlush(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void flush() {
        repository.flush();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void deleteInBatch(Iterable<T> itrbl) {
        repository.deleteInBatch(itrbl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void delete(Iterable<? extends T> itrbl) {
        repository.delete(itrbl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
    rollbackFor = Throwable.class)
    public void deleteAll() {
        repository.deleteAll();
    }
}
