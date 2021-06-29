package com.harunisik.userdata.controller;


import static com.harunisik.userdata.constant.UrlConstants.CREATE_USERDATA_URL;
import static com.harunisik.userdata.constant.UrlConstants.GET_ALL_USERDATA_URL;
import static com.harunisik.userdata.constant.UrlConstants.GET_USERDATA_BY_ID_URL;
import static com.harunisik.userdata.constant.UrlConstants.UPDATE_USERDATA_URL;
import static com.harunisik.userdata.exception.ExceptionType.USERDATA_NOT_FOUND;
import static com.harunisik.userdata.util.JsonUtils.objectToJson;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.harunisik.userdata.ObjectFactory;
import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.dto.UserdataUpdateRequest;
import com.harunisik.userdata.exception.GlobalExceptionHandler;
import com.harunisik.userdata.exception.UserdataException;
import com.harunisik.userdata.service.UserdataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@ComponentScan({"com.example.*"})
public class UserdataControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserdataController unit;

    @Mock
    private UserdataService userdataService;

    public static String id = "id";
    public static String teamA = "teamA";
    public static String userdataA = "userdataA";
    public static String teamB = "teamB";
    public static String userdataB = "userdataB";
    public static String errorCode = "errorCode";
    public static String description = "description";
    public static final String JSON_EXPRESSION = "$['%s']";
    public static final String JSON_EXPRESSION2 = "$.%s[%s]['%s']";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(unit)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    public void shouldGetAllUserdata() throws Exception {

        when(userdataService.getAllUserdata()).thenReturn(singletonList(
            ObjectFactory.buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B)));

        mockMvc.perform(get(GET_ALL_USERDATA_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "userdataList", 0, id)).value(ObjectFactory.USERDATA_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "userdataList", 0, teamA)).value(ObjectFactory.TEAM_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "userdataList", 0, userdataA)).value(ObjectFactory.USERDATA_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "userdataList", 0, teamB)).value(ObjectFactory.TEAM_B))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "userdataList", 0, userdataB)).value(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldGetUserdataById() throws Exception {

        when(userdataService.getUserdataById(ObjectFactory.USERDATA_ID)).thenReturn(
            ObjectFactory.buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B));

        mockMvc.perform(get(GET_USERDATA_BY_ID_URL, "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.USERDATA_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamA)).value(ObjectFactory.TEAM_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataA)).value(ObjectFactory.USERDATA_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamB)).value(ObjectFactory.TEAM_B))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataB)).value(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldThrowException_getUserdataById() throws Exception {

        doThrow(new UserdataException(USERDATA_NOT_FOUND)).when(userdataService).getUserdataById(any());

        this.mockMvc
            .perform(get(GET_USERDATA_BY_ID_URL, "11"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorCode)).value(USERDATA_NOT_FOUND.getCode()))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(USERDATA_NOT_FOUND.getMessage()));
    }

    @Test
    public void shouldCreateUserdata() throws Exception {

        UserdataCreateRequest userdataCreateRequest = ObjectFactory.buildUserdataCreateRequest();

        when(userdataService.createUserdata(userdataCreateRequest)).thenReturn(
            ObjectFactory.buildUserdataResponse(ObjectFactory.USERDATA_A, ObjectFactory.USERDATA_B));

        mockMvc.perform(post(CREATE_USERDATA_URL)
            .content(objectToJson(userdataCreateRequest))
            .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.USERDATA_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamA)).value(ObjectFactory.TEAM_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataA)).value(ObjectFactory.USERDATA_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamB)).value(ObjectFactory.TEAM_B))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataB)).value(ObjectFactory.USERDATA_B));
    }

    @Test
    public void shouldUpdateUserdata() throws Exception {

        UserdataUpdateRequest userdataUpdateRequest = ObjectFactory.buildUserdataUpdateRequest();
        UserdataResponse userdataResponse = ObjectFactory
            .buildUserdataResponse(ObjectFactory.UPDATED_USERDATA_A, ObjectFactory.UPDATED_USERDATA_B);

        when(userdataService.updateUserdata(ObjectFactory.USERDATA_ID, userdataUpdateRequest)).thenReturn(userdataResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(UPDATE_USERDATA_URL, ObjectFactory.USERDATA_ID)
            .content(objectToJson(userdataUpdateRequest))
            .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.USERDATA_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamA)).value(ObjectFactory.TEAM_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataA)).value(ObjectFactory.UPDATED_USERDATA_A))
            .andExpect(jsonPath(format(JSON_EXPRESSION, teamB)).value(ObjectFactory.TEAM_B))
            .andExpect(jsonPath(format(JSON_EXPRESSION, userdataB)).value(ObjectFactory.UPDATED_USERDATA_B));
    }

    @Test
    public void shouldThrowException_updateUserdata() throws Exception {

        doThrow(new UserdataException(USERDATA_NOT_FOUND)).when(userdataService)
            .updateUserdata(anyLong(), any());

        this.mockMvc
            .perform(MockMvcRequestBuilders.post(UPDATE_USERDATA_URL, ObjectFactory.USERDATA_ID)
                .content(objectToJson(ObjectFactory.buildUserdataUpdateRequest()))
                .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorCode)).value(USERDATA_NOT_FOUND.getCode()))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(USERDATA_NOT_FOUND.getMessage()));
    }

}
