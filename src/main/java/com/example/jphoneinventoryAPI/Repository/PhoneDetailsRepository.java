package com.example.jphoneinventoryAPI.Repository;

import com.example.jphoneinventoryAPI.Models.PhoneDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneDetailsRepository extends MongoRepository<PhoneDetails, String>
{}
