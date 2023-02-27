package com.example.backend.aqms.ControllerClass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.aqms.EntityClass.AqmsEntity;
import com.example.backend.aqms.RepositoryClass.DynamoDbRepository;

@CrossOrigin(origins = "*")
@RestController
public class controller {
    
    @Autowired
    private DynamoDbRepository dRepository;

    /* gives the lastest recorded data in the first floor */
    @GetMapping("/firstFloor")
    public AqmsEntity firstFloor() {
        return dRepository.first_floor_check(dRepository.findAll());
    }

    /* checks if the device is up or not */
    @GetMapping("/")
    public List<AqmsEntity> completeData() {
        return dRepository.findAll();
    }

    /* gives the lastest recorded data in the second floor */
    @GetMapping("/secondFloor")
    public AqmsEntity secondFloor() {
        return dRepository.second_floor_check(dRepository.findAll());
    }

    /* gives the lastest recorded data in the third floor */
    @GetMapping("/thirdFloor")
    public AqmsEntity thirdFloor() {
        return dRepository.third_floor_check(dRepository.findAll());
    }

    /* gives the complete recorded data in the office */
    @GetMapping("/airQualityInfo")
    public List<AqmsEntity> allData() {
        return dRepository.findAll();
    }
}

