package com.harunisik.userdata.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Harun Isik on 27/07/21.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserdataCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String sex;

    @NotNull
    private String age;

    @NotNull
    private String country;

}
