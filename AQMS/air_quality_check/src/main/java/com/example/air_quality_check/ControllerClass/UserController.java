package com.example.air_quality_check.ControllerClass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.air_quality_check.EntityClass.entity;
import com.example.air_quality_check.ServiceClass.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String noUrl() {
        return "Please enter /data to view the info about air quality in given floors, or enter /airQualityInfo to check the purity of air!! Thank you";
    }

    /* to display all the data present in the database */
    @GetMapping("data")
    public List <entity> list() {
        return userService.listAllData();
    }
    
    /* to update the aqi based on gas levels and display the data */
    @GetMapping("airQualityInfo")
    public List<entity> air_quality() {
        return userService.air_quality_check(userService.listAllData());
    }
}
