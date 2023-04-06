package com.noirix.domain.hibernate;

import com.noirix.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private Double weight;

    @Column
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
