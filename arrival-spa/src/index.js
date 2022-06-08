import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import TicketsList from './TicketsList';
import TicketForm from './TicketForm';
import reportWebVitals from './reportWebVitals';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
     <Routes>
      <Route path="/testapp" element={<App />} >
        <Route path="/testapp" element={<TicketForm />} />
        <Route path="/testapp/*" element={<TicketForm />} />
        <Route path="/testapp/tickets-list" element={<TicketsList />} />
      </Route>
    </Routes>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
