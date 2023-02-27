import AppNavbar from './AppNavbar';
import React, { Component } from 'react';
import { Container, Label, Row, Col } from 'reactstrap';
import BackendService from '../services/BackendService';

import '../../App.css';

class ThirdFloor extends Component {

    /* uses a backend service to get all the data present in the first floor */
    constructor(props) {
        super(props);
        this.state = {
        items: [],
        };
    }
    /* stores the stream data */
    componentDidMount() {
        BackendService.getThirdFloor()
        .then((response) =>  this.setState({items: response.data}))
        .catch(error => {
            console.error(error);
          });
        
        };
  
    /* this shows the lastest aqi record in that floor */
    render() {
        const {items } = this.state;
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Row style={{marginTop:"20px"}}>
                        <Col sm="12" md={{ size: 4, offset: 4 }}>
                            <div style={{marginTop:"20px"}}>
                                <Label for="welcome"><strong>Here is the third floor data, {items.timeStamp} :-)</strong></Label>
                            </div>

                            <div style={{marginTop:"20px"}} className='login-form'> 
                                
                                <ol key = { items.timeStamp } >
                                    <div style={{marginTop:"10px"}}> 
                                        <Label for="floor"><strong>Floor:{ "  " +items.floor}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>      
                                        <Label for="o2_level"><strong>Oxygen Level:{ "  " + items.o2_level}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>
                                        <Label for="co2_level"><strong>CO2 level:{ "  " + items.co2_level}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>    
                                        <Label for="so2_level"><strong>SO2 level:{"  " + items.so2_level}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>      
                                        <Label for="co_level"><strong>CO level:{ "  " + items.co_level}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>
                                        <Label for="c_level"><strong>C level:{ "  " + items.c_level}</strong></Label>
                                    </div>

                                    <div style={{marginTop:"10px"}}>    
                                        <Label for="air_quality_level"><strong>AQI:{"  " + items.aqms_message}</strong></Label>
                                    </div>
                                </ol>
                                            
                            </div>

                        </Col>
                    </Row>
                </Container>     
            </div>
        );
    }
}


export default ThirdFloor;
