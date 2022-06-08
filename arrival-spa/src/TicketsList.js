
import {useState, useEffect} from 'react';
import {Table, Card} from 'react-bootstrap';

function TicketsList() {

  const [list, setList] = useState([]);

  useEffect(()=>{

    fetch('/testapp/api/tickets-list')
    .then(response => response.json())
    .then(data => {
      setList(data)
      console.log(data);
    });

  },[])

  return (

    <Table striped bordered hover responsive>
      <thead>
        <tr>
          <th>#</th>
          <th>Topic</th>
          <th>Description</th>
          <th>Client</th>
          <th>Contractor</th>
          <th>Due Date</th>
        </tr>
      </thead>
      <tbody>
          {list.map((ticket, i)=>{
            return(<tr key={i}>
              <td>{ticket[0]}</td>
              <td>{ticket[1]}</td>
              <td>{ticket[2]}</td>
              <td>{ticket[3]}</td>
              <td>{ticket[4]}</td>
              <td>{(new Date(ticket[5]*1000).toString())}</td>
            </tr>)
          })}
      </tbody>
    </Table>

  );
}

export default TicketsList;
