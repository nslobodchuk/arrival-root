import {Form, Button, FloatingLabel, InputGroup, FormControl, Col, Row, Container} from 'react-bootstrap';
import {useState, useEffect} from 'react';

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

function TicketForm() {


  const [date, setDate] = useState(new Date());
  const [topic, setTopic] = useState('');
  const [details, setDetails] = useState('');
  const [client, setClient] = useState('');
  const [contractor, setContractor] = useState('');
  const [submitting, setSubmitting] = useState(false);

   async function submit(){

     setSubmitting(true)

     const body = JSON.stringify({ 
            topic: topic,
            details: details,
            client: client,
            contractor: contractor,
            date: Math.floor(date.getTime() / 1000)}); //Unix timestamp

     console.log(body);

      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: body
      };

      const response = await fetch('/testapp/api/submit', requestOptions);
      const data = await response.json();
      setSubmitting(false);
      console.log(data);
    }

    return (

            <Container>
            <h1>Submit a New Ticket</h1>
            <p>Fill out the form below to submit a new ticket</p>
            <Form>
              <Form.Group className="mb-3" controlId="TicketTopic">
                <Form.Label><h3>Topic</h3></Form.Label>
                <Form.Control placeholder="Ticket topic" onChange={e=>setTopic(e.target.value)} value={topic}/>
              </Form.Group>

              <Form.Group className="mb-3" controlId="TicketDescription">
                <Form.Label><h3>Provide Details</h3></Form.Label>
                <Form.Control as="textarea" rows={3} placeholder="Provide details" onChange={e=>setDetails(e.target.value)} value={details}/>
              </Form.Group>

              <Form.Group className="mb-3" controlId="TicketClient">
                <Form.Label><h3>Client Name</h3></Form.Label>
                <Form.Control placeholder="Client Name"  onChange={e=>setClient(e.target.value)} value={client}/>
              </Form.Group>


              <Form.Group className="mb-3" controlId="TicketContractor">
                <Form.Label><h3>Contractor</h3></Form.Label>
                <Form.Control placeholder="Contractor"  onChange={e=>setContractor(e.target.value)} value={contractor}/>
              </Form.Group>

               <Form.Group className="mb-3" controlId="TicketDate">
                <Form.Label><h3>Due Date</h3></Form.Label>
                <Form.Control placeholder="Due Date mm/dd/yyyy" value={date} disabled/>
                <br/>

                  <Col align="center">
                  <DatePicker selected={date} onChange={(date) => setDate(date)} inline/>
                </Col>
                      
                <br/>

              </Form.Group>


              <Row>
                  <Button variant="primary"  
                  disabled={submitting}
                  onClick={!submitting ? submit : null}>
                    {submitting ? 'Submitting...' : 'Click to Submit'}
                  </Button>
              </Row>
              <br/>
            </Form>
            </Container>


    );
}

export default TicketForm;
