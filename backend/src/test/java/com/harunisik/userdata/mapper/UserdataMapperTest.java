package com.harunisik.userdata.mapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.harunisik.userdata.ObjectFactory;
import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.model.Userdata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserdataMapperTest {

    UserdataMapperImpl customerAccountMapper = new UserdataMapperImpl();

    @Test
    public void toUserdataResponse() {
        Userdata userdata = ObjectFactory.buildUserdata();
        UserdataResponse userdataResponse = customerAccountMapper.toUserdataResponse(userdata);

        assertThat(userdataResponse.getId(), is(userdata.getId()));
//        assertThat(userdataResponse.getTeamA(), is(userdata.getTeamA()));
//        assertThat(userdataResponse.getUserdataA(), is(userdata.getUserdataA()));
//        assertThat(userdataResponse.getTeamB(), is(userdata.getTeamB()));
//        assertThat(userdataResponse.getUserdataB(), is(userdata.getUserdataB()));
    }

    @Test
    public void toUserdata() {
        UserdataCreateRequest userdataCreateRequest = ObjectFactory.buildUserdataCreateRequest();
        Userdata userdata = customerAccountMapper.toUserdata(userdataCreateRequest);

//        assertThat(userdata.getTeamA(), is(userdataCreateRequest.getTeamA()));
//        assertThat(userdata.getUserdataA(), is(userdataCreateRequest.getUserdataA()));
//        assertThat(userdata.getTeamB(), is(userdataCreateRequest.getTeamB()));
//        assertThat(userdata.getUserdataB(), is(userdataCreateRequest.getUserdataB()));
    }
}