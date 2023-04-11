package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateUserRepositoryImpl implements HibernateUserRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public HibernateUser findOne(Long id) {
        final String findByIdHQL = "select u from HibernateUser u where u.id = " + id;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findByIdHQL, HibernateUser.class).getSingleResult();
    }

    @Override
    public List<HibernateUser> findAll() {

        final String findAllHQL = "select u from HibernateUser u";
//        //final String findAllNative = "select * from users";
//
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery(findAllHQL, HibernateUser.class).getResultList();
//        }


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateUser.class).getResultList();
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return update(object);
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.saveOrUpdate(object);
            session.flush();

            transaction.commit();

            return object;
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HibernateUser> searchUser(String query, Double weight) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findByEmail(String email) {
        return Optional.empty();
    }
}
