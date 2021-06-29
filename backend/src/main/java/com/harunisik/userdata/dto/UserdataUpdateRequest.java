package com.harunisik.userdata.dto;

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
public class UserdataUpdateRequest {

    private String name;
    private String age;
    private String sex;
    private String country;

}
