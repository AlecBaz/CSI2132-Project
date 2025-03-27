import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/admin.css';

function AdminPanel() {
  const [entries, setEntries] = useState({ customers: {}, employees: {}, hotels: {}, rooms: {} });
  const [deleteEntry, setDeleteEntry] = useState({ customers: '', employees: '', hotels: '', rooms: '' });
  const [editEntries, setEditEntries] = useState({ id: '', newEntry: '' });

  const handleChange = (e, section, field) => {
    setEntries({ ...entries, [section]: { ...entries[section], [field]: e.target.value } });
  };

  const handleDeleteChange = (e, section) => {
    setDeleteEntry({ ...deleteEntry, [section]: e.target.value });
  };

  const handleEditChange = (e, field) => {
    setEditEntries({ ...editEntries, [field]: e.target.value });
  };

  const handleAdd = (section) => {
    console.log(`Add to ${section}:`, entries[section]);
  };

  const handleDelete = (section) => {
    console.log(`Delete from ${section}: ID ${deleteEntry[section]}`);
  };

  const handleEdit = (section) => {
    console.log(`Edit ${section}: ID ${editEntries.id} to ${editEntries.newEntry}`);
  };

  return (
    <div className="admin-page">
      <header className="top-nav">
        <div className="logo">MyHotel ADMIN</div>
        <div className="sql-buttons1">
          <Link to="/admin">SQL View 1</Link>
        </div>
        <div className="sql-buttons2">
          <Link to="/admin">SQL View 2</Link>
        </div>
      </header>

      <div className="admin-container">
        {['customers', 'employees', 'hotels', 'rooms'].map((section) => (
          <section key={section} className={`${section}-admin`}>
            <h2 className="section-title">{section.charAt(0).toUpperCase() + section.slice(1)}</h2>
            <div className="section-content">
              <h3>Add {section}</h3>
              {section === 'customers' && (
                <>
                  <input type="text" placeholder="Customer Name" onChange={(e) => handleChange(e, section, 'customer_name')} />
                  <input type="text" placeholder="Customer Address" onChange={(e) => handleChange(e, section, 'customer_address')} />
                  <input type="text" placeholder="ID Type" onChange={(e) => handleChange(e, section, 'id_type')} />
                </>
              )}
              {section === 'employees' && (
                <>
                  <input type="text" placeholder="Employee Name" onChange={(e) => handleChange(e, section, 'employee_name')} />
                  <input type="text" placeholder="Employee Address" onChange={(e) => handleChange(e, section, 'employee_address')} />
                  <input type="text" placeholder="SIN" onChange={(e) => handleChange(e, section, 'SIN')} />
                  <input type="text" placeholder="Position" onChange={(e) => handleChange(e, section, 'employee_position')} />
                </>
              )}
              {section === 'hotels' && (
                <>
                  <input type="text" placeholder="Hotel Name" onChange={(e) => handleChange(e, section, 'hotel_name')} />
                  <input type="number" placeholder="Rating" onChange={(e) => handleChange(e, section, 'rating')} />
                  <input type="text" placeholder="Hotel Address" onChange={(e) => handleChange(e, section, 'hotel_address')} />
                </>
              )}
              {section === 'rooms' && (
                <>
                  <input type="text" placeholder="Room View" onChange={(e) => handleChange(e, section, 'view')} />
                  <input type="text" placeholder="Amenities" onChange={(e) => handleChange(e, section, 'amentities')} />
                  <input type="text" placeholder="Damages" onChange={(e) => handleChange(e, section, 'damages')} />
                </>
              )}
              <button onClick={() => handleAdd(section)}>Confirm Add</button>

              <h3>Delete {section}</h3>
              <input type="text" placeholder={`Enter ${section} ID to delete`} value={deleteEntry[section]} onChange={(e) => handleDeleteChange(e, section)} />
              <button onClick={() => handleDelete(section)}>Confirm Delete</button>

              <h3>Edit {section}</h3>
              {section === 'customers' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Customer Name" onChange={(e) => handleChange(e, section, 'customer_name')} />
                  <input type="text" placeholder="Customer Address" onChange={(e) => handleChange(e, section, 'customer_address')} />
                  <input type="text" placeholder="ID Type" onChange={(e) => handleChange(e, section, 'id_type')} />
                  <input type="date" placeholder="Registration Date" onChange={(e) => handleChange(e, section, 'registration_date')} />
                </>
              )}
              {section === 'employees' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Employee Name" onChange={(e) => handleChange(e, section, 'employee_name')} />
                  <input type="text" placeholder="Employee Address" onChange={(e) => handleChange(e, section, 'employee_address')} />
                  <input type="text" placeholder="SIN" onChange={(e) => handleChange(e, section, 'SIN')} />
                  <input type="text" placeholder="Position" onChange={(e) => handleChange(e, section, 'employee_position')} />
                </>
              )}
              {section === 'hotels' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Hotel Name" onChange={(e) => handleChange(e, section, 'hotel_name')} />
                  <input type="number" placeholder="Rating" onChange={(e) => handleChange(e, section, 'rating')} />
                  <input type="text" placeholder="Hotel Address" onChange={(e) => handleChange(e, section, 'hotel_address')} />
                </>
              )}
              {section === 'rooms' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Room View" onChange={(e) => handleChange(e, section, 'view')} />
                  <input type="text" placeholder="Amenities" onChange={(e) => handleChange(e, section, 'amentities')} />
                  <input type="text" placeholder="Damages" onChange={(e) => handleChange(e, section, 'damages')} />
                </>
              )}
              <button onClick={() => handleEdit(section)}>Confirm Edit</button>
            </div>
          </section>
        ))}
      </div>
    </div>
  );
}

export default AdminPanel;
