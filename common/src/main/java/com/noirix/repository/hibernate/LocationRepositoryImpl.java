package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationRepositoryImpl implements HibernateLocationRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public HibernateLocation findOne(Long id) {
        return null;
    }

    @Override
    public List<HibernateLocation> findAll() {
        final String findAllHQL = "select l from HibernateLocation l";

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateLocation.class).getResultList();
    }

    @Override
    public HibernateLocation create(HibernateLocation object) {
        return null;
    }

    @Override
    public HibernateLocation update(HibernateLocation object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
