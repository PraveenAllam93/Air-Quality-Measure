package com.example.data.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.iot.client.AWSIotException;
import com.example.data.Service.service;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableScheduling
@RestController
public class controller {
    int count = 0;
    @Autowired
    private service userService;
    /* scheduling the controller to run continously */
    @Scheduled(fixedRate = 10000)
    @GetMapping("/addData")
    public String saveDepartment() throws AWSIotException, JsonProcessingException, InterruptedException {
        userService.saveData();
        count += 1;
        return "Message published successfully";
    }
    
}
