package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    private Long id;
    private String login;
    private String password;
    private String phone_number;
    private String email;
    private String passport_series_and_number;

    private Timestamp created;
    private Timestamp changed;

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
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
