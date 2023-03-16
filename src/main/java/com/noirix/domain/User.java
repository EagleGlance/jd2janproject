package com.noirix.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private String password;
    private String phone_number;
    private String email;
    private String passport_series_and_number;

    private Timestamp created;
    private Timestamp changed;

    public User() {
    }

    public User(Long id, String login, String password, String phone_number, String email,
                String passport_series_and_number, Timestamp created, Timestamp changed) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.passport_series_and_number = passport_series_and_number;
        this.created = created;
        this.changed = changed;
    }

    public User(Long id, String login, String password, String phone_number, String email,
                String passport_series_and_number, Timestamp changed) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.passport_series_and_number = passport_series_and_number;
        this.changed = changed;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport_series_and_number() {
        return passport_series_and_number;
    }

    public void setPassport_series_and_number(String passport_series_and_number) {
        this.passport_series_and_number = passport_series_and_number;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone_number, user.phone_number) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passport_series_and_number, user.passport_series_and_number) &&
                Objects.equals(created, user.created) &&
                Objects.equals(changed, user.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, phone_number, email, passport_series_and_number, created, changed);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", passport_series_and_number='" + passport_series_and_number + '\'' +
                ", created='" + created + '\'' +
                ", changed='" + changed + '\'' +
                '}';
    }
}
