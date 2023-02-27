package com.example.backend.aqms.Config;

import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;

@Configuration
public class dynamoDBConfig {

    private static final Logger logger = LoggerFactory.getLogger(dynamoDBConfig.class);
    // Load values from the application.properties file
    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;
    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;
    @Value("${amazon.aws.region}")
    private String awsRegion;

    // Create a bean for the DynamoDBMapper object
    @Bean
    public DynamoDBMapper dynamoDBMapper() throws UnknownHostException {
        return new DynamoDBMapper(amazonDynamoDb());
    }

    private AmazonDynamoDB amazonDynamoDb() throws UnknownHostException {
        try{
            logger.info("Dynamo db connection established successfully");
              // Create an AmazonDynamoDB object using the builder pattern
            return AmazonDynamoDBClientBuilder.standard()
                                              .withEndpointConfiguration(
                                                    new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, awsRegion))
                                              .withCredentials(amazonDynamoDbCredentials()).build();
            } catch(AmazonDynamoDBException e){
                logger.error("Please do check your AWS Access key or Secret Key");
            } catch(SdkClientException e) {
                logger.error("Unable to execute HTTP request, please do check DynamoDB endpoint");
            }
         return null;
    }
    //Static credentials are provided
    private AWSCredentialsProvider amazonDynamoDbCredentials() {
        try{
            logger.info("The AWS Access key and Secret key are correct");
            return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
        } catch(AmazonDynamoDBException e){
            logger.error("Please do check your AWS Access key or Secret Key", e);
        }
        return null;
        
    }
     
    // a bean for cross cofiguration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

}

