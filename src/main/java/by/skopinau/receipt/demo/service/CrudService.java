package by.skopinau.receipt.demo.service;

import by.skopinau.receipt.demo.dal.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudService<E extends BaseEntity> {
    Optional<E> save(E entity);

    List<E> findAll();

    Optional<E> findById(int id);

    Optional<E> update(int id, E entity);

    boolean deleteById(int id);
}
