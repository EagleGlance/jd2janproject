package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateUserRepositoryImpl implements HibernateUserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public HibernateUser findOne(Long id) {
        return null;
    }

    @Override
    public List<HibernateUser> findAll() {

        final String findAllHQL = "select u from HibernateUser u";
        //final String findAllNative = "select * from users";

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(findAllHQL, HibernateUser.class).getResultList();
        }
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return null;
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return null;
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
