import React, { Component } from 'react';
import { Nav, Navbar, NavLink } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import '../../App.css';


/* App NavBar which contains the navigation options like sigin, signup, signout*/

class AppNavbar extends Component {
    
    render() {
        return <Navbar color="dark" dark expand="md">
            <Nav className="mr-auto" >
                <NavLink href="/firstFloor" className="nav-container">First Floor</NavLink>
                <NavLink href="/secondFloor" className="nav-container">Second Floor</NavLink>
                <NavLink href="/thirdFloor" className="nav-container">Third Floor</NavLink>
                <NavLink href="/allData" className="nav-container">All Data</NavLink>
            </Nav>
            
        </Navbar>;
    }
}

export default withRouter(AppNavbar);