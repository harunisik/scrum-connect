package com.harunisik.userdata.exception;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserdataExceptionResponse {

    private String errorCode;
    private String description;
    private Timestamp timestamp;

}
