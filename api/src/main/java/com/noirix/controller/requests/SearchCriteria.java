package com.noirix.controller.requests;

import com.noirix.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "Search criteria object: search by weight, text description, gender")
public class SearchCriteria {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "query", type = "string", description = "text query")
    @NotNull
    @Size(min = 1, max = 100)
    private String query;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "50", type = "double", description = "user weight")
    @NotNull
    @Size(min = 1, max = 1000)
    private String weight;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "NOT_SELECTED", type = "Gender", description = "user gender")
    @NotNull
    private Gender gender;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
