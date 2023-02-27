import AppNavbar from './AppNavbar';
import React, { Component } from 'react';
import BackendService from '../services/BackendService';
import '../../App.css';

/* uses a backend service to get all the data present in the all floors */
class AllData extends Component {

    constructor(props) {
        super(props);
        this.state = {
        items: [],
        };
    }

    /* stores the data in the form of json */
    componentDidMount() {
        BackendService.getAllData()
        .then((response) =>  this.setState({items: response.data}))
        .catch(error => {
            console.error(error);
          });
        
    };

    
  
    /* renders the json data */
    render() {

        const getColor = (aqms, isError) => {
            if (isError === "False") {
                if (aqms === 'Air Quality is good and healthy :-)') return 'green';
                else if (aqms === 'Air Quality is hazardous, please do vacate the floor immediately, DANGER DANGER!!!!!' || aqms === 'Air Quality is unhealthy, please do vacate the floor!!!!' || aqms === 'Air Quality is extremely unhealthy, please do vacate the floor immediately!!!!!') return 'red';
                else if (aqms === "Air Quality is which is unhealthy for sensetive people, please do vacate the floor if you're sensetive :-)") return 'orange';
                return '#90EE90';
            }
            return '';
        };

        const gasColor = (aqms, isError) => {
            if (isError === "False") {
                if (aqms >= 150 && aqms <= 200) return 'red';
                else if (aqms >= 100 && aqms <= 149) return 'orange';
                else if (aqms >= 70 && aqms <= 99) return '#90EE90';
            }
            return '';
        }

        const o2Color = (aqms, isError) => {
            if(isError === "False") {
                if (aqms <= 200) return 'red';
                else if (aqms >= 200 && aqms <= 400) return 'orange';
                else if (aqms >= 401 && aqms <= 500) return '#90EE90';
            }
            return '';
        }
        
        const checkError = (isError) => {
            if (isError === "True") return 'red';
        }

        const {items } = this.state;
        return (
            <div>
                <AppNavbar/>
                                {/* <Label for="welcome"><strong>AQI Data</strong></Label>  */}
                                <table>
                                   <thead>
                                        <tr>
                                            <th ><strong>timeStamp</strong></th>
                                            <th ><strong>floor</strong></th>
                                            <th ><strong>o2_level</strong></th>
                                            <th ><strong>co2_level</strong></th>
                                            <th ><strong>so2_level</strong></th>
                                            <th ><strong>co_level</strong></th>
                                            <th ><strong>c_level</strong></th>
                                            <th ><strong>air_quality_level</strong></th>
                                        </tr>
                                   </thead>
                                    
                                   <tbody>
                                        {items.map((item) => ( 
                                            <tr key = { item.id } style = {{color: 'black', background: checkError(item.isError)}}> 
                                                    <td><strong>{item.timeStamp}</strong></td>
                                                    <td><strong>{item.floor}</strong></td>
                                                    <td style={{ color: o2Color(item.o2_level, item.isError) }}>{(item.isError === "True") ? '####' : <strong>{item.o2_level}</strong>}</td>
                                                    <td style={{ color: gasColor(item.co2_level, item.isError) }}> {(item.isError === "True") ? '####' : <strong>{item.co2_level}</strong>}</td>
                                                    <td style={{ color: gasColor(item.so2_level, item.isError) }}> {(item.isError === "True") ? '####' : <strong>{item.so2_level}</strong>}</td>
                                                    <td style={{ color: gasColor(item.co_level, item.isError) }}> {(item.isError === "True") ? '####' : <strong>{item.co_level}</strong>}</td>
                                                    <td style={{ color: gasColor(item.c_level, item.isError) }}> {(item.isError === "True") ? '####' : <strong>{item.c_level}</strong>}</td>
                                                    <td style={{ color: getColor(item.aqms_message, item.isError) }}> <strong>{item.aqms_message}</strong></td>
                                                </tr>
                                        ))}
                                    </tbody>
  
                                </table>

                                
                                
                            </div>
                   
        );
    }
}

export default AllData;