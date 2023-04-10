import React, {useState} from 'react';
import Navbar from '../Components/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Cache from './Cache';
import Forecast from './Forecast';
import Current from './Current';

function App() {
  
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/current" element={<Current/>} />
        <Route path="/forecast" element={<Forecast/>} />
        <Route path="/cache" element={<Cache/>} />
      </Routes>
    </Router>
  );
}

export default App;
