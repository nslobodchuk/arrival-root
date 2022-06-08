import './App.css';
import { Link, Outlet} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar, Container, Row, Col, Image, Nav} from 'react-bootstrap';
import ArrivalSVG from './arrival-svg';


function App() {
  return (

    <>
      <Navbar bg="dark" variant="dark" >
        <Container style={{ maxHeight: '3em' }} >
          <Navbar.Brand as={Link} to="" >
            <ArrivalSVG/>
          </Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/testapp"><h4>Submit Ticket</h4></Nav.Link>
            <Nav.Link as={Link} to="/testapp/tickets-list"><h4>All Tickets</h4></Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <br/>
<Row>
      <Col lg="1"></Col>
      <Col lg="10"><Outlet/></Col>
      <Col lg="1"></Col>
</Row>

    </>
  );
}

export default App;
