//Imports
import React from 'react';

//Imports Bootstrap
import { Container, Navbar,Nav } from 'react-bootstrap';

class Header extends React.Component {

    render(){
        return (
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="#home">MediScreen</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="patients">Patients</Nav.Link>
                        <Nav.Link href="notes">Notes</Nav.Link>
                        <Nav.Link href="reports">Reports</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        )
    }
}
export default Header;