package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.noirix.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "locations", "cars"
})
@ToString(exclude = {
        "locations", "cars"
})
@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private Double weight;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "password", column = @Column(name = "user_password"))
    })
    private AuthenticationInfo authenticationInfo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateLocation> locations = Collections.emptySet();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateCars> cars = Collections.emptySet();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private HibernatePersonalDocument document;
}
