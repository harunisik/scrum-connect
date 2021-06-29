package com.harunisik.userdata.service;

import static java.util.stream.Collectors.toList;

import com.harunisik.userdata.exception.ExceptionType;
import com.harunisik.userdata.exception.UserdataException;
import com.harunisik.userdata.mapper.UserdataMapper;
import com.harunisik.userdata.model.Userdata;
import com.harunisik.userdata.repository.UserdataRepository;
import com.harunisik.userdata.dto.UserdataResponse;
import com.harunisik.userdata.dto.UserdataCreateRequest;
import com.harunisik.userdata.dto.UserdataUpdateRequest;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Harun Isik on 27/07/21.
 */

@Service
public class UserdataService {

    @Autowired
    private UserdataRepository userdataRepository;

    @Autowired
    private UserdataMapper userdataMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserdataService.class);

    public List<UserdataResponse> getAllUserdata() {
        logger.info("getting all userdata from the db...");

        List<Userdata> userdataList = userdataRepository.findAll();
        logger.info("{} userdata(s) found in the db", userdataList.size());

        return userdataList.stream()
            .map(userdataMapper::toUserdataResponse)
            .collect(toList());
    }

    public UserdataResponse getUserdataById(Long id) {
        logger.info("getting userdata with id {}...", id);

        Optional<Userdata> userdata = userdataRepository.findById(id);

        if (userdata.isPresent()) {
            logger.info("userdata found with id {}", id);
            return userdataMapper.toUserdataResponse(userdata.get());
        }

        logger.info("userdata not found with id {}", id);
        throw new UserdataException(ExceptionType.USERDATA_NOT_FOUND);
    }

    public UserdataResponse createUserdata(UserdataCreateRequest userdataCreateRequest) {
        logger.info("creating a new userdata with the info {}...", userdataCreateRequest);

        Userdata userdata = userdataMapper.toUserdata(userdataCreateRequest);
        userdataRepository.save(userdata);
        logger.info("userdata created successfully with id {}", userdata.getId());
        return userdataMapper.toUserdataResponse(userdata);
    }

    public UserdataResponse updateUserdata(Long id, UserdataUpdateRequest userdataUpdateRequest) {
        logger.info("updating userdata with id {} and info {}...", id, userdataUpdateRequest);

        Optional<Userdata> userdataOpt = userdataRepository.findById(id);
        if (userdataOpt.isPresent()) {
            logger.info("userdata found with id {}", id);
            Userdata userdata = userdataOpt.get();
            userdata.setName(userdataUpdateRequest.getName());
            userdata.setAge(userdataUpdateRequest.getAge());
            userdata.setSex(userdataUpdateRequest.getSex());
            userdata.setCountry(userdataUpdateRequest.getCountry());
            userdataRepository.save(userdata);

            logger.info("userdata updated successfully with id {}", id);

            return userdataMapper.toUserdataResponse(userdata);
        }

        throw new UserdataException(ExceptionType.USERDATA_NOT_FOUND);
    }

    public void deleteUserdata(Long id) {
        logger.info("deleting userdata with id {}...", id);

        Optional<Userdata> userdataOpt = userdataRepository.findById(id);
        if (userdataOpt.isPresent()) {
            logger.info("userdata found with id {}", id);

            userdataRepository.delete(userdataOpt.get());

            logger.info("userdata deleted successfully with id {}", id);

            return;
        }

        throw new UserdataException(ExceptionType.USERDATA_NOT_FOUND);
    }
}
