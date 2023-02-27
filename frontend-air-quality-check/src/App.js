import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import FirstFloor from './app/components/FirstFloor';
import SecondFloor from './app/components/SecondFloor';
import ThirdFloor from './app/components/ThirdFloor';
import AllData from './app/components/AllData';

/* redirecting to different pages with help of routes */

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/allData' exact={true} component={AllData}/>
          <Route path='/' exact={true} component={FirstFloor}/>
          <Route path='/firstFloor' exact={true} component={FirstFloor}/>
          <Route path='/secondFloor' exact={true} component={SecondFloor}/>
          <Route path='/thirdFloor' exact={true} component={ThirdFloor}/>
        </Switch>
      </Router>
    )
  }
}

export default App;