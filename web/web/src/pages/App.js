import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Health from '../pages/Health';
import Calculator from '../pages/Calculator';
import Diet from '../pages/Diet';
import Mobile from '../pages/Mobile';

function App() {
  return (
    <Router>
        <Route exact path="/" component={Health}/>
        <Route exact path="/calculator" component={Calculator}/>
        <Route exact path="/diet" component={Diet}/>
        <Route exact path="/mobile" component={Mobile}/>
    </Router>
  );
}

export default App;
