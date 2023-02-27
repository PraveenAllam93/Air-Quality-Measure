package com.example.data.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.example.data.dto.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MQTTConfig {
    private static final Logger logger = LoggerFactory.getLogger(MQTTConfig.class);

    @Value("${amazon.aws.clientEndpoint}")
    private String clientEndpoint;
    @Value("${amazon.aws.accesskey}")
    private String awsAccessKeyId;
    @Value("${amazon.aws.secretkey}")
    private String awsSecretAccessKey;
    @Value("${amazon.aws.clientId_floor1}")
    private String clientId_floor1;
    @Value("${amazon.aws.clientId_floor2}")
    private String clientId_floor2;
    @Value("${amazon.aws.clientId_floor3}")
    private String clientId_floor3;
    
    AWSIotMqttClient client_floor1 = null;
    AWSIotMqttClient client_floor2 = null;
    AWSIotMqttClient client_floor3 = null;

    public AWSIotMqttClient connectToIot(String clientId) {
        return new AWSIotMqttClient(clientEndpoint, clientId, awsAccessKeyId, awsSecretAccessKey, null);
    }

    public void connectionMessage(String floor) {
        logger.info("Connected to the floor " + floor + "thing!");
        System.out.println("Connected to the floor " + floor + "thing!");
    }
    /* creating a connection for three things and sending each floor data into the respective thing */
    public void connectToThing1() throws AWSIotException {
        client_floor1 = connectToIot(clientId_floor1);
        client_floor1.connect();
        connectionMessage("1");
    }

    public void connectToThing2() throws AWSIotException {
        client_floor2 = connectToIot(clientId_floor2);
        client_floor2.connect();
        connectionMessage("2");
    }

    public void connectToThing3() throws AWSIotException {
        client_floor3 = connectToIot(clientId_floor3);
        client_floor3.connect();
        connectionMessage("3");
    }
    /* publishing data into the things respectively */
    public void publish(String data, int floor) throws AWSIotException {
        AWSIotQos qos = AWSIotQos.QOS0;
        String topic = "aqms";
        long timeout = 10000; 
        MyMessage message = new MyMessage(topic, qos, data);
        if (floor == 1) {
            client_floor1.publish(message, timeout);
            return;
        }
        if (floor == 2){
            client_floor2.publish(message, timeout);
            return;
        }
        client_floor3.publish(message, timeout);
        return;
    }

    

    
}
