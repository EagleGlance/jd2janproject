package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "users"
})
@ToString(exclude = {
        "users"
})
@Entity
@Cacheable("locations")
@javax.persistence.Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "locations")
public class HibernateLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column
    @JsonIgnore
    private Boolean visible;

    @ManyToMany
    @JoinTable(name = "l_users_locations",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("locations")
    private Set<HibernateUser> users = Collections.emptySet();

}
