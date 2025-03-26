import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';
import Search from "./pages/search.js";
import Admin from './pages/admin.js';
import Employee from './pages/employee.js';

import ChainComponent from "./pages/Testpage.js";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Search />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/employee" element={<Employee />} />
        <Route path="/chain/:chainId" element={<ChainComponent />} />
      </Routes>
    </Router>
  );
}

export default App;
