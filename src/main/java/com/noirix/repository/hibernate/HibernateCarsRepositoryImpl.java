package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateCars;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateCarsRepositoryImpl implements HibernateCarsRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public HibernateCars findOne(Long id) {
        return null;
    }

    @Override
    public List<HibernateCars> findAll() {
        final String findAllHQL = "select c from HibernateCars c";

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateCars.class).getResultList();
    }

    @Override
    public HibernateCars create(HibernateCars object) {
        return null;
    }

    @Override
    public HibernateCars update(HibernateCars object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
