package com.harunisik.userdata.controller;

import static com.harunisik.userdata.constant.UrlConstants.CREATE_USERDATA_URL;
import static com.harunisik.userdata.constant.UrlConstants.GET_ALL_USERDATA_URL;
import static com.harunisik.userdata.constant.UrlConstants.GET_USERDATA_BY_ID_URL;
import static com.harunisik.userdata.constant.UrlConstants.UPDATE_USERDATA_URL;
import static org.springframework.http.HttpStatus.CREATED;

import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataListResponse;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.dto.UserdataUpdateRequest;
import com.harunisik.userdata.service.UserdataService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Harun Isik on 27/07/21.
 */

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserdataController {

    private static final Logger logger = LoggerFactory.getLogger(UserdataController.class);

    @Autowired
    private UserdataService userdataService;

    @GetMapping(GET_ALL_USERDATA_URL)
    public ResponseEntity<UserdataListResponse> getAllUserdata() {
        logger.info("get userdata called.");

        List<UserdataResponse> userdataList = userdataService.getAllUserdata();
        UserdataListResponse userdataListResponse = UserdataListResponse.builder()
            .userdataList(userdataList)
            .build();

        return ResponseEntity.ok().body(userdataListResponse);
    }

    @GetMapping(GET_USERDATA_BY_ID_URL)
    public ResponseEntity<UserdataResponse> getUserdataById(@Valid @PathVariable(value = "id") String id) {
        logger.info("get userdata by id called.");
        UserdataResponse userdataResponse = userdataService.getUserdataById(Long.parseLong(id));
        return ResponseEntity.ok().body(userdataResponse);
    }

    @PostMapping(CREATE_USERDATA_URL)
    public ResponseEntity<UserdataResponse> createUserdata(@Valid @RequestBody UserdataCreateRequest userdataCreateRequest) {
        logger.info("create userdata called.");
        UserdataResponse userdataResponse = userdataService.createUserdata(userdataCreateRequest);
        return ResponseEntity.status(CREATED).body(userdataResponse);
    }

    @PostMapping(UPDATE_USERDATA_URL)
    public ResponseEntity<UserdataResponse> updateUserdata(
        @Valid @PathVariable(value = "id") String id,
        @Valid @RequestBody UserdataUpdateRequest userdataUpdateRequest) {
        logger.info("update userdata called.");

        UserdataResponse userdataResponse = userdataService.updateUserdata(Long.parseLong(id), userdataUpdateRequest);
        return ResponseEntity.ok().body(userdataResponse);
    }

    @DeleteMapping(UPDATE_USERDATA_URL)
    public ResponseEntity deleteUserdata(
        @Valid @PathVariable(value = "id") String id) {
        logger.info("delete userdata called.");

        userdataService.deleteUserdata(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }
}