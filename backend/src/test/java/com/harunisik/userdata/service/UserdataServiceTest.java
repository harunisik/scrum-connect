package com.harunisik.userdata.service;

import static com.harunisik.userdata.exception.ExceptionType.USERDATA_NOT_FOUND;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

import com.harunisik.userdata.ObjectFactory;
import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.dto.UserdataUpdateRequest;
import com.harunisik.userdata.exception.UserdataException;
import com.harunisik.userdata.mapper.UserdataMapper;
import com.harunisik.userdata.model.Userdata;
import com.harunisik.userdata.repository.UserdataRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
@ComponentScan({"com.example.*"})
class UserdataServiceTest {

    @InjectMocks
    private UserdataService unit;

    @Mock
    private UserdataRepository userdataRepository;

    @Mock
    private UserdataMapper userdataMapper;

    @Test
    public void shouldGetAllUserdatas() {

        Userdata userdata = ObjectFactory.buildUserdata();
        UserdataResponse userdataResponse = ObjectFactory
            .buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B);

        when(userdataRepository.findAll()).thenReturn(singletonList(userdata));
        when(userdataMapper.toUserdataResponse(userdata)).thenReturn((userdataResponse));

        List<UserdataResponse> userdataResponseList = unit.getAllUserdata();

        UserdataResponse result = userdataResponseList.get(0);

        verify(userdataRepository).findAll();
        verify(userdataMapper).toUserdataResponse(userdata);

        assertThat(result.getId(), comparesEqualTo(ObjectFactory.USERDATA_ID));
//        assertThat(result.getTeamA(), comparesEqualTo(ObjectFactory.TEAM_A));
//        assertThat(result.getUserdataA(), comparesEqualTo(ObjectFactory.USERDATA_A));
//        assertThat(result.getTeamB(), comparesEqualTo(ObjectFactory.TEAM_B));
//        assertThat(result.getUserdataB(), comparesEqualTo(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldGetUserdataById() {

        Userdata userdata = ObjectFactory.buildUserdata();
        UserdataResponse userdataResponse = ObjectFactory
            .buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B);

        when(userdataRepository.findById(ObjectFactory.USERDATA_ID)).thenReturn(Optional.of(userdata));
        when(userdataMapper.toUserdataResponse(userdata)).thenReturn((userdataResponse));

        UserdataResponse result = unit.getUserdataById(ObjectFactory.USERDATA_ID);

        verify(userdataRepository).findById(ObjectFactory.USERDATA_ID);
        verify(userdataMapper).toUserdataResponse(userdata);

        assertThat(result.getId(), comparesEqualTo(ObjectFactory.USERDATA_ID));
//        assertThat(result.getTeamA(), comparesEqualTo(ObjectFactory.TEAM_A));
//        assertThat(result.getUserdataA(), comparesEqualTo(ObjectFactory.USERDATA_A));
//        assertThat(result.getTeamB(), comparesEqualTo(ObjectFactory.TEAM_B));
//        assertThat(result.getUserdataB(), comparesEqualTo(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldThrowException_getUserdataById_whenUserdataNotFound() {

        UserdataException exceptionThrown = assertThrows(UserdataException.class,
            () -> unit.getUserdataById(ObjectFactory.USERDATA_ID));

        //THEN
        verify(userdataRepository).findById(ObjectFactory.USERDATA_ID);

        assertThat(USERDATA_NOT_FOUND.getMessage(), is(exceptionThrown.getMessage()));
        assertThat(USERDATA_NOT_FOUND.getCode(), is(exceptionThrown.getExceptionType().getCode()));
    }

    @Test
    public void shouldCreateUserdata() {

        Userdata userdata = ObjectFactory.buildUserdata();
        UserdataCreateRequest userdataCreateRequest = ObjectFactory.buildUserdataCreateRequest();
        UserdataResponse userdataResponse = ObjectFactory
            .buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B);

        when(userdataMapper.toUserdata(userdataCreateRequest)).thenReturn(userdata);
        when(userdataMapper.toUserdataResponse(userdata)).thenReturn(userdataResponse);

        UserdataResponse result = unit.createUserdata(userdataCreateRequest);

        verify(userdataMapper).toUserdata(userdataCreateRequest);
        verify(userdataRepository).save(userdata);
        verify(userdataMapper).toUserdataResponse(userdata);

        assertThat(result.getId(), comparesEqualTo(ObjectFactory.USERDATA_ID));
//        assertThat(result.getTeamA(), comparesEqualTo(ObjectFactory.TEAM_A));
//        assertThat(result.getUserdataA(), comparesEqualTo(ObjectFactory.USERDATA_A));
//        assertThat(result.getTeamB(), comparesEqualTo(ObjectFactory.TEAM_B));
//        assertThat(result.getUserdataB(), comparesEqualTo(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldUpdateUserdata() {

        Userdata userdata = ObjectFactory.buildUserdata();
        UserdataUpdateRequest userdataUpdateRequest = ObjectFactory.buildUserdataUpdateRequest();
        UserdataResponse userdataResponse = ObjectFactory
            .buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B);

        when(userdataRepository.findById(ObjectFactory.USERDATA_ID)).thenReturn(Optional.of(userdata));
        when(userdataMapper.toUserdataResponse(userdata)).thenReturn((userdataResponse));

        UserdataResponse result = unit.updateUserdata(ObjectFactory.USERDATA_ID, userdataUpdateRequest);

        verify(userdataRepository).findById(ObjectFactory.USERDATA_ID);
        verify(userdataRepository).save(userdata);
        verify(userdataMapper).toUserdataResponse(userdata);

        assertThat(result.getId(), comparesEqualTo(ObjectFactory.USERDATA_ID));
//        assertThat(result.getTeamA(), comparesEqualTo(ObjectFactory.TEAM_A));
//        assertThat(result.getUserdataA(), comparesEqualTo(ObjectFactory.USERDATA_A));
//        assertThat(result.getTeamB(), comparesEqualTo(ObjectFactory.TEAM_B));
//        assertThat(result.getUserdataB(), comparesEqualTo(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldThrowException_updateUserdata_whenUserdataNotFound() {

        UserdataException exceptionThrown = assertThrows(UserdataException.class,
            () -> unit.updateUserdata(ObjectFactory.USERDATA_ID, ObjectFactory.buildUserdataUpdateRequest()));

        //THEN
        verify(userdataRepository).findById(ObjectFactory.USERDATA_ID);

        assertThat(USERDATA_NOT_FOUND.getMessage(), is(exceptionThrown.getMessage()));
        assertThat(USERDATA_NOT_FOUND.getCode(), is(exceptionThrown.getExceptionType().getCode()));
    }
}