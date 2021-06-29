package com.harunisik.userdata;

import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.dto.UserdataUpdateRequest;
import com.harunisik.userdata.model.Userdata;

public class ObjectFactory {

    public static Long USERDATA_ID = 1L;
    public static String TEAM_A = "Leeds United";
    public static Integer USERDATA_A = 2;
    public static Integer UPDATED_USERDATA_A = 3;
    public static String TEAM_B = "Manchester City";
    public static Integer USERDATA_B = 1;
    public static Integer UPDATED_USERDATA_B = 4;


    public static UserdataResponse buildUserdataResponse(Integer userdataA, Integer userdataB) {
        return UserdataResponse.builder()
            .id(USERDATA_ID)
//            .teamA(TEAM_A)
//            .userdataA(userdataA)
//            .teamB(TEAM_B)
//            .userdataB(userdataB)
            .build();
    }

    public static UserdataCreateRequest buildUserdataCreateRequest() {
        return UserdataCreateRequest.builder()
//            .teamA(TEAM_A)
//            .userdataA(USERDATA_A)
//            .teamB(TEAM_B)
//            .userdataB(USERDATA_B)
            .build();
    }

    public static UserdataUpdateRequest buildUserdataUpdateRequest() {
        return UserdataUpdateRequest.builder()
//            .userdataA(UPDATED_USERDATA_A)
//            .userdataB(UPDATED_USERDATA_B)
            .build();
    }

    public static Userdata buildUserdata() {
        return Userdata.builder()
//            .id(USERDATA_ID)
//            .teamA(TEAM_A)
//            .userdataA(USERDATA_A)
//            .teamB(TEAM_B)
//            .userdataB(USERDATA_B)
            .build();
    }
}
