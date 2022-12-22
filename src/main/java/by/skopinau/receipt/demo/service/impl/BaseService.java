package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.BaseEntity;
import by.skopinau.receipt.demo.dal.repository.BaseRepository;
import by.skopinau.receipt.demo.service.CrudService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@CacheConfig(cacheNames="application")
public abstract class BaseService<E extends BaseEntity> implements CrudService<E> {
    private final BaseRepository<E> repository;

    protected BaseService(BaseRepository<E> repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    @Cacheable
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Optional<E> update(int id, E entity) {
        if (repository.existsById(id)) {
            entity.setId(id);
            return save(entity);
        }

        return Optional.empty();
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteById(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
