package com.noirix.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PageCriteria {

    @NotNull
    private int page;

    @NotNull
    private int offset;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}


