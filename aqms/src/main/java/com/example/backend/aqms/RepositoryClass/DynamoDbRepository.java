package com.example.backend.aqms.RepositoryClass;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.backend.aqms.EntityClass.AqmsEntity;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    private static final Logger logger = LoggerFactory.getLogger(DynamoDbRepository.class);
     /* to find all data present */
    public List<AqmsEntity> findAll(){
        try{
            List<AqmsEntity> totalData = dynamoDBMapper.scan(AqmsEntity.class, new DynamoDBScanExpression()).stream().sorted(Comparator.comparing(AqmsEntity::getTimeStamp)).collect(Collectors.toList());
            int size = totalData.size();
            if (size == 0) throw new NoSuchElementException("No data found, there might be some error");
            String lastRecordedTimeString = totalData.get(totalData.size() - 1).getTimeStamp();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime lastRecordedTime = LocalDateTime.parse(lastRecordedTimeString, formatter);
            int time_check = timeDifference(lastRecordedTime);
            if(time_check == 1) {
                logger.info("The device is down for more than 30 minutes, please check");
            }
            logger.info("The complete aqi data is fetched successfully");
            if (size > 100) return totalData.subList(size - 100, size - 1);
            else return totalData;
        } catch (NoSuchElementException e) {
            logger.info("No data found");
        } catch (IndexOutOfBoundsException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        }
        return null;
    }

     /* to find lastest recorded data present in the 1st floor */
     public AqmsEntity first_floor_check(List<AqmsEntity> aqi) {
        try {
            aqi = aqi.stream().filter(i -> i.getFloor() == 1).collect(Collectors.toList());
            logger.info("The lastest readings of aqi data in the first floor is fetched successfully");
            return aqi.get(aqi.size() - 1);

        } catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        } catch (IndexOutOfBoundsException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        }
        return null;
    }

    /* to find lastest recorded data present in the 2nd floor */
    public AqmsEntity second_floor_check(List<AqmsEntity> aqi) {
        try {
            aqi = aqi.stream().filter(i -> i.getFloor() == 2).collect(Collectors.toList());
            logger.info("The lastest readings of aqi data in the second floor is fetched successfully");
            return aqi.get(aqi.size() - 1);
        }
        catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        }
        catch (IndexOutOfBoundsException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        } 
        return null;  
    }

    /* to find lastest recorded data present in the 3rd floor */
    public AqmsEntity third_floor_check(List<AqmsEntity> aqi) {
        try {
            aqi = aqi.stream().filter(i -> i.getFloor() == 3).collect(Collectors.toList());
            logger.info("The lastest readings of aqi data in the third floor is fetched successfully");
            return aqi.get(aqi.size() - 1);
        }
        catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        } catch (IndexOutOfBoundsException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        }
        return null;
    }
    /* checking the time differnece between latest record and the current time */
    public int timeDifference(LocalDateTime lastRecordedTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(lastRecordedTime, currentTime);
        System.out.println("The Minutes : " + duration.toMinutes());
        if (duration.toMinutes() > 30) {
            return 1;
        } else {
            return 0;
        }
    }
}
