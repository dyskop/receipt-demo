package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.BaseEntity;
import by.skopinau.receipt.demo.dal.repository.BaseRepository;
import by.skopinau.receipt.demo.service.CrudService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<E extends BaseEntity> implements CrudService<E> {
    private final BaseRepository<E> repository;

    protected BaseService(BaseRepository<E> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> update(int id, E entity) {
        if (repository.existsById(id)) {
            entity.setId(id);
            return save(entity);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
