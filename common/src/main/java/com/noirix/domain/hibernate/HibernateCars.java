package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "user"
})
@ToString(exclude = {
        "user"
})
@Entity
@Table(name = "cars")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HibernateCars {

    @Id
    @GeneratedValue(generator = "cars_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cars_id_seq", sequenceName = "cars_id_seq", allocationSize = 1, initialValue = 100)
    private Long id;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private Float price;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private HibernateUser user;
}
