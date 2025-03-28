import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';
import Search from "./pages/search.js";
import Admin from './pages/admin.js';
import Employee from './pages/employee.js';
import Booking from './pages/booking.js';
import View1 from './pages/view1.js';
import View2 from './pages/view2.js';

import ChainComponent from "./pages/Testpage.js";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Search />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/employee" element={<Employee />} />
        <Route path="/view1" element={<View1 />} />
        <Route path="/view2" element={<View2 />} />
        <Route path="/booking/:roomID" element={<Booking />} />
        <Route path="/chain/:chainId" element={<ChainComponent />} />
      </Routes>
    </Router>
  );
}

export default App;
