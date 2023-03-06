package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    private Long id;
    private String name;
    private String brand;
    private Float price;
    private Long user_id;
    private Timestamp created;
    private Timestamp changed;
    private Boolean is_deleted;

    @Override
    public String toString() {
        return "\n" + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
