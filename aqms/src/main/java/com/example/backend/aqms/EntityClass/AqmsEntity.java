package com.example.backend.aqms.EntityClass;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
//Defing all the attributes present in the dynamo table and generating getters and setters
@Data
@DynamoDBTable(tableName = "aqms_final_data")
public class AqmsEntity {
    
    @DynamoDBHashKey
    @DynamoDBAttribute
    private String timeStamp;
    @DynamoDBAttribute
    private int floor;
    @DynamoDBAttribute
    private String  o2_level;
    @DynamoDBAttribute
    private String co2_level;
    @DynamoDBAttribute
    private String so2_level;
    @DynamoDBAttribute
    private String co_level;
    @DynamoDBAttribute
    private String c_level;
    @DynamoDBAttribute
    private String aqms_message;
    @DynamoDBAttribute
    private String air_quality_index;
    @DynamoDBAttribute
    private String isError;
    
    //non parameterised constructor
    public AqmsEntity() {
    }
    //parameterised constructor
    public AqmsEntity(String timeStamp, int floor, String o2_level, String co2_level, String so2_level, String co_level, String c_level,
            String aqms_message, String air_quality_index, String isError) {       
        this.timeStamp = timeStamp;
        this.floor = floor;
        this.o2_level = o2_level;
        this.co2_level = co2_level;
        this.so2_level = so2_level;
        this.co_level = co_level;
        this.c_level = c_level;
        this.aqms_message = aqms_message;
        this.air_quality_index = air_quality_index;
        this.isError = isError;
    }
    public int getFloor() { return floor; }

}