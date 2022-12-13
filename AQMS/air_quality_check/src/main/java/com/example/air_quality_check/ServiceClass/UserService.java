package com.example.air_quality_check.ServiceClass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.air_quality_check.EntityClass.entity;
import com.example.air_quality_check.RepositoryClass.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    /* to find all the data present in the sql table */
    public List<entity> listAllData() {
        /* returns all the data present in the repository in the form of list */
        return userRepository.findAll();
    }

    /* to check the airquality measure, and to indicate the level of air quality present around */
    public List<entity> air_quality_check(List<entity> aqi) {
        for (int i = 0; i < aqi.size(); i++ ){

            /* calculating the aqi with formula (oxygen level - sum of all gases level) */
            double air_quality_index = aqi.get(i).getCo2_level() + aqi.get(i).getC_level() + aqi.get(i).getCo_level() + aqi.get(i).getSo2_level() - aqi.get(i).getO2_level();

            /* aqi <= 50 is healthy */
            if (air_quality_index <= 50) {
                aqi.get(i).setAir_quality_level("Air Quality is good and healthy :-)");
            }

            /* aqi <= 100 is moderate */
            else if (air_quality_index >= 51 && air_quality_index <= 100) {
                aqi.get(i).setAir_quality_level("Air Quality is " + air_quality_index + ", which is Moderate!!!!");
            }

            /* aqi <= 150 is unhealthy for some set of people */
            else if (air_quality_index >= 101 && air_quality_index <= 150) {
                aqi.get(i).setAir_quality_level("Air Quality is " + air_quality_index + ", which is unhealthy for sensetive people, please do vacate the floor " +aqi.get(i).getFloor() + " if you're sensetive :-)");
            }

            /*aqi >= 151 is dangerous */
            else if (air_quality_index >= 151 && air_quality_index <= 200) {
                aqi.get(i).setAir_quality_level("Air Quality is " + air_quality_index + ", which is unhealthy, please do vacate the floor " +aqi.get(i).getFloor() + "!!!!");
            }

            else if (air_quality_index >= 201 && air_quality_index <= 300) {
                aqi.get(i).setAir_quality_level("Air Quality is " + air_quality_index + ", which is extremely unhealthy, please do vacate the floor " +aqi.get(i).getFloor() + " immediately!!!!!");
            }

            else if (air_quality_index >= 301) {
                aqi.get(i).setAir_quality_level("Air Quality is " + air_quality_index + ", which is hazardous, please do vacate the floor " +aqi.get(i).getFloor() + " immediately, DANGER DANGER!!!!!");
            }

        }

        /* returning the repository values in the form list with updated aqi value */
        return userRepository.findAll();
    }

}
