package com.harunisik.userdata.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.harunisik.userdata.model.Userdata;
import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataResponse;
import org.mapstruct.Mapper;

/**
 * Created by Harun Isik on 27/07/21.
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserdataMapper {

    UserdataResponse toUserdataResponse(final Userdata userdata);

    Userdata toUserdata(final UserdataCreateRequest userdataCreateRequest);
}
