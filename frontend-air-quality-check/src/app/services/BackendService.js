import axios from 'axios';



class BackendService {

    /* get the complete data from the ebs endpoint */
    async getAllData() {
        return await axios.get('http://aqmsfinal-env.eba-hzzp5d8a.us-east-1.elasticbeanstalk.com/airQualityInfo');
    }
    /* get the first floor data from the ebs endpoint */
    async getFirstFloor() {
        return await axios.get('http://aqmsfinal-env.eba-hzzp5d8a.us-east-1.elasticbeanstalk.com/firstFloor');
    }
    /* get the second floor data from the ebs endpoint */
    async getSecondFloor() {
        return await axios.get('http://aqmsfinal-env.eba-hzzp5d8a.us-east-1.elasticbeanstalk.com/secondFloor');
    }
    /* get the third floor data from the ebs endpoint */
    async getThirdFloor() {
        return await axios.get('http://aqmsfinal-env.eba-hzzp5d8a.us-east-1.elasticbeanstalk.com/thirdFloor');
    }
}

export default new BackendService();