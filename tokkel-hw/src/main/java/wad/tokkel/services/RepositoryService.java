package wad.tokkel.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryService<T> {

    JpaRepository<T, Integer> getRepository();

    Iterable<T> findAll();

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pgbl);

    T findOne(Integer id);

    boolean exists(Integer id);

    long count();

    Iterable<T> save(Iterable<? extends T> itrbl);

    T save(T t);

    T saveAndFlush(T t);

    void flush();

    void deleteInBatch(Iterable<T> itrbl);

    void delete(Integer id);

    void delete(T t);

    void delete(Iterable<? extends T> itrbl);

    void deleteAll();
}
