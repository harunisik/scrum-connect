package com.harunisik.userdata.repository;

import com.harunisik.userdata.model.Userdata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserdataRepository extends MongoRepository<Userdata, Long> {
}
