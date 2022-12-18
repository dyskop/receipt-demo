package by.skopinau.receipt.demo.dal.repository;

import by.skopinau.receipt.demo.dal.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Integer> {
}
