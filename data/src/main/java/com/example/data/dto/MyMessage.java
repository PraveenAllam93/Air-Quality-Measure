package com.example.data.dto;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;

public class MyMessage extends AWSIotMessage {
    public MyMessage(String topic, AWSIotQos qos, String payload) {
        super(topic, qos, payload);
    }
    @Override
    public void onSuccess() {
        System.out.println("Message published successfully");
    }
    @Override
    public void onFailure() {
        System.out.println("Message publishing failed");
    }
    @Override
    public void onTimeout() {
        System.out.println("Timeout!!!!!!!");
    }
}
