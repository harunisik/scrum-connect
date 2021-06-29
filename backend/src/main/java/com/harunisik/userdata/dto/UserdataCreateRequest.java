package com.harunisik.userdata.dto;

import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String sex;

    @NotEmpty
    private String age;

    @NotEmpty
    private String country;

}
