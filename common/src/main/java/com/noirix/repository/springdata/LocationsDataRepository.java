package com.noirix.repository.springdata;

import com.noirix.domain.hibernate.HibernateLocation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationsDataRepository extends JpaRepository<HibernateLocation, Long> {

    @Cacheable("locations")
    List<HibernateLocation> findByVisible(Boolean visibleFlag);

}
