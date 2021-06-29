package com.harunisik.userdata.dto;

import java.util.List;
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
public class UserdataListResponse {

    private List<UserdataResponse> userdataList;

}
