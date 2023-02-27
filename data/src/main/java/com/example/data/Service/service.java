package com.example.data.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.iot.client.AWSIotException;
import com.example.data.util.MQTTConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class service {
    private static final Logger logger = LoggerFactory.getLogger(service.class);
    int flag = 0;

    @Autowired
    MQTTConfig mqttConfig;
    /* conencting to the things and sending some random data into the thing respectively */
    public String saveData() throws AWSIotException, JsonProcessingException, InterruptedException {
        int n = 3, floor = 0;
        if (flag == 0) {
            mqttConfig.connectToThing1();
            mqttConfig.connectToThing2();
            mqttConfig.connectToThing3();
        }
        flag = 1;
        while ( n > 0) {
            floor += 1;
            int co2_level, so2_level, co_level, c_level, o2_level;

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            
            /* generating some random values of gases */
            co2_level = getRandomNumber(75, 200);
            so2_level = getRandomNumber(75, 200);
            co_level = getRandomNumber(75, 200);
            c_level = getRandomNumber(75, 200);
            o2_level = getRandomNumber(100, 1000);

            /* JSON object to store the data inkey value pair */
            JSONObject obj = new JSONObject();
            obj.put("timeStamp", formattedTime);
            obj.put("floor", floor);
            obj.put("co2_level", String.valueOf(co2_level));
            obj.put("so2_level", String.valueOf(so2_level));
            obj.put("c_level", String.valueOf(c_level));
            obj.put("o2_level", String.valueOf(o2_level));
            obj.put("co_level", String.valueOf(co_level));

            String jsonStr = obj.toString();
            System.out.println(jsonStr);
            /* publish the data into IoT core */
            mqttConfig.publish(jsonStr, floor);
            System.out.println("Data Added Successfully!!!");
            logger.info("Data added successfully for the floor, " + floor +"!");
            n -= 1;
            Thread.sleep(1000);
        }
        return "Data Added Successfully!!!";
    }
    
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
