package com.noirix.repository.springdata;

import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface UserDataRepository extends
        JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long>,
        CrudRepository<HibernateUser, Long> {

    List<HibernateUser> findByIdAndBirthDateAndSurname(Long id, Timestamp birthDate, String surname);

    List<HibernateUser> findByIdInAndGenderAndFullNameOrderByWeightDescBirthDateAsc(List<Long> ids, Gender gender, String fullName);

    HibernateUser countByName(String name);

    @Query(value = "select u.email, c.brand from users as u inner join cars c on u.id = c.user_id", nativeQuery = true)
    List<Object[]> findColumnsFromDifferentTables();

    @Query("select u from HibernateUser u")
    List<HibernateUser> findUsers();

    @Query(value = "select u from HibernateUser u where u.authenticationInfo.email = :email and u.surname = :surname")
    List<HibernateUser> findByHQLQuery(String email, @Param("surname") String name);

    Optional<HibernateUser> findByAuthenticationInfoEmail(String email);

    @Modifying(flushAutomatically = true)
    @Query(value = "update users set full_name = :email ", nativeQuery = true)
    void updateField(String email);

    @Query(name = "m_users_multiple_ids_search")
    List<HibernateUser> namedQuery(String userIds);

}
