import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/admin.css';

function AdminPanel() {
  // Local state for each CRUD operation (for demonstration)
  const [customerData, setCustomerData] = useState({ insert: '', modify: '', remove: '' });
  const [employeeData, setEmployeeData] = useState({ insert: '', modify: '', remove: '' });
  const [hotelData, setHotelData] = useState({ insert: '', modify: '', remove: '' });
  const [roomData, setRoomData] = useState({ insert: '', modify: '', remove: '' });

  // For demo purposes, these functions just log the values.
  const handleSubmit = (category, action, value) => {
    console.log(`Category: ${category} - Action: ${action} - Data: ${value}`);
    // Here, you would make your API call.
  };

  const renderCrudSection = (title, data, setData, category) => (
    <div className="crud-section">
      <h3>{title}</h3>
      <div className="crud-row">
        <label>Insert:</label>
        <input
          type="text"
          placeholder={`Insert new ${title}`}
          value={data.insert}
          onChange={(e) => setData({ ...data, insert: e.target.value })}
        />
        <button onClick={() => handleSubmit(category, 'insert', data.insert)}>Submit</button>
      </div>
      <div className="crud-row">
        <label>Modify:</label>
        <input
          type="text"
          placeholder={`Modify existing ${title}`}
          value={data.modify}
          onChange={(e) => setData({ ...data, modify: e.target.value })}
        />
        <button onClick={() => handleSubmit(category, 'modify', data.modify)}>Submit</button>
      </div>
      <div className="crud-row">
        <label>Remove:</label>
        <input
          type="text"
          placeholder={`Remove ${title}`}
          value={data.remove}
          onChange={(e) => setData({ ...data, remove: e.target.value })}
        />
        <button onClick={() => handleSubmit(category, 'remove', data.remove)}>Submit</button>
      </div>
    </div>
  );

  return (
    <div className="admin-page">
      <header className="admin-header">
        <h1>Admin Panel</h1>
      </header>
      <div className="admin-content">
        {renderCrudSection('Customers', customerData, setCustomerData, 'Customers')}
        {renderCrudSection('Employees', employeeData, setEmployeeData, 'Employees')}
        {renderCrudSection('Hotels', hotelData, setHotelData, 'Hotels')}
        {renderCrudSection('Rooms', roomData, setRoomData, 'Rooms')}
        <div className="sql-views">
          <button className="sql-button" onClick={() => console.log('View SQL Views')}>
            View SQL Views
          </button>
        </div>
      </div>
      <footer className="admin-footer">
        <Link to="/"></Link>
      </footer>
    </div>
  );
}

export default AdminPanel;
