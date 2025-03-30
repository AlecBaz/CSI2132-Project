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

  const handleAdd = async (section) => {
    console.log(`Add to ${section}:`, entries[section]);
    if (section === 'customers') {
      try {
        const response = await fetch('http://localhost:8080/api/customer/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            customerName: entries[section].customer_name,
            customerAddress: entries[section].customer_address,
            idType: entries[section].id_type,
            idNumber: entries[section].id_number,
          }),
        });
        if (response.ok) {
          console.log('Customer added successfully.');
          setEntries({ ...entries, [section]: {} }); // Clear input fields
        } else {
          console.error('Failed to add customer.');
        }
      } catch (error) {
        console.error('Error adding customer:', error);
      }
    }
    if (section === 'employees') {
      try {
        const response = await fetch('http://localhost:8080/api/employee/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            employeeId: parseInt(entries[section].employee_id, 10), // Include employee_id
            employeeName: entries[section].employee_name,
            employeeAddress: entries[section].employee_address,
            SIN: parseInt(entries[section].SIN, 10), // Ensure SIN is passed as an integer
            position: entries[section].employee_position,
            hotelId: parseInt(entries[section].hotel_id, 10), // Include hotel_id
          }),
        });
        if (response.ok) {
          console.log('Employee added successfully.');
          setEntries({ ...entries, [section]: {} }); // Clear input fields
        } else {
          console.error('Failed to add employee.');
        }
      } catch (error) {
        console.error('Error adding employee:', error);
      }
    }
    if (section === 'hotels') {
      try {
        const response = await fetch('http://localhost:8080/api/hotel/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            hotelId: parseInt(entries[section].hotel_id, 10),
            chainId: parseInt(entries[section].chain_id, 10),
            hotelName: entries[section].hotel_name,
            rating: parseInt(entries[section].rating, 10),
            hotelAddress: entries[section].hotel_address,
            city: entries[section].city,
            state: entries[section].state,
            amountOfRooms: parseInt(entries[section].amount_of_rooms, 10),
            contactEmail: entries[section].contact_email,
            contactPhone: entries[section].contact_phone,
            managerId: parseInt(entries[section].manager_id, 10),
          }),
        });
        if (response.ok) {
          console.log('Hotel added successfully.');
          setEntries({ ...entries, [section]: {} }); // Clear input fields
        } else {
          console.error('Failed to add hotel.');
        }
      } catch (error) {
        console.error('Error adding hotel:', error);
      }
    }
    if (section === 'rooms') {
      try {
        const response = await fetch('http://localhost:8080/api/rooms/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            roomId: parseInt(entries[section].room_id, 10),
            hotelId: parseInt(entries[section].hotel_id, 10),
            price: parseFloat(entries[section].price),
            view: entries[section].view,
            amentities: entries[section].amentities,
            extendable: entries[section].extendable === 'true',
            capacity: parseInt(entries[section].capacity, 10),
            damages: entries[section].damages,
          }),
        });
        if (response.ok) {
          console.log('Room added successfully.');
          setEntries({ ...entries, [section]: {} }); // Clear input fields
        } else {
          console.error('Failed to add room.');
        }
      } catch (error) {
        console.error('Error adding room:', error);
      }
    }
  };

  const handleDelete = async (section) => {
    console.log(`Delete from ${section}: ID ${deleteEntry[section]}`);
    if (section === 'customers') {
      try {
        const response = await fetch(`http://localhost:8080/api/customer/delete/${deleteEntry[section]}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          console.log(`Customer with ID ${deleteEntry[section]} deleted successfully.`);
          setDeleteEntry({ ...deleteEntry, [section]: '' }); // Clear input
        } else {
          console.error(`Failed to delete customer with ID ${deleteEntry[section]}`);
        }
      } catch (error) {
        console.error('Error deleting customer:', error);
      }
    }
    if (section === 'rooms') {
      try {
        const response = await fetch(`http://localhost:8080/api/rooms/delete/${deleteEntry[section]}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          console.log(`Room with ID ${deleteEntry[section]} deleted successfully.`);
          setDeleteEntry({ ...deleteEntry, [section]: '' }); // Clear input
        } else {
          console.error(`Failed to delete room with ID ${deleteEntry[section]}`);
        }
      } catch (error) {
        console.error('Error deleting room:', error);
      }
    }
    if (section === 'hotels') {
      try {
        const response = await fetch(`http://localhost:8080/api/hotel/delete/${deleteEntry[section]}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          console.log(`Hotel with ID ${deleteEntry[section]} deleted successfully.`);
          setDeleteEntry({ ...deleteEntry, [section]: '' }); // Clear input
        } else {
          console.error(`Failed to delete hotel with ID ${deleteEntry[section]}`);
        }
      } catch (error) {
        console.error('Error deleting hotel:', error);
      }
    }
    if (section === 'employees') {
      try {
        const response = await fetch(`http://localhost:8080/api/employee/delete/${deleteEntry[section]}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          console.log(`Employee with ID ${deleteEntry[section]} deleted successfully.`);
          setDeleteEntry({ ...deleteEntry, [section]: '' }); // Clear input
        } else {
          console.error(`Failed to delete employee with ID ${deleteEntry[section]}`);
        }
      } catch (error) {
        console.error('Error deleting employee:', error);
      }
    }
  };

  const handleEdit = async (section) => {
    console.log(`Edit ${section}: ID ${editEntries.id} to ${editEntries.newEntry}`);
    if (section === 'customers') {
      try {
        const response = await fetch(`http://localhost:8080/api/customer/edit/${editEntries.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            customerName: entries[section].customer_name,
            customerAddress: entries[section].customer_address,
            idType: entries[section].id_type,
            idNumber: entries[section].id_number,
            registrationDate: entries[section].registration_date,
          }),
        });
        if (response.ok) {
          console.log(`Customer with ID ${editEntries.id} updated successfully.`);
          setEditEntries({ id: '', newEntry: '' }); // Clear input
        } else {
          console.error(`Failed to update customer with ID ${editEntries.id}`);
        }
      } catch (error) {
        console.error('Error updating customer:', error);
      }
    }
    if (section === 'rooms') {
      try {
        const response = await fetch(`http://localhost:8080/api/rooms/edit/${editEntries.id}/${entries[section].hotel_id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            price: parseFloat(entries[section].price),
            view: entries[section].view,
            amentities: entries[section].amentities,
            extendable: entries[section].extendable === 'true',
            capacity: parseInt(entries[section].capacity, 10),
            damages: entries[section].damages,
          }),
        });
        if (response.ok) {
          console.log(`Room with ID ${editEntries.id} and Hotel ID ${entries[section].hotel_id} updated successfully.`);
          setEditEntries({ id: '', newEntry: '' }); // Clear input
        } else {
          console.error(`Failed to update room with ID ${editEntries.id} and Hotel ID ${entries[section].hotel_id}`);
        }
      } catch (error) {
        console.error('Error updating room:', error);
      }
    }
    if (section === 'hotels') {
      try {
        const response = await fetch(`http://localhost:8080/api/hotel/edit/${editEntries.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            hotelName: entries[section].hotel_name,
            rating: parseInt(entries[section].rating, 10),
            hotelAddress: entries[section].hotel_address,
            city: entries[section].city,
            state: entries[section].state,
            amountOfRooms: parseInt(entries[section].amount_of_rooms, 10),
            contactEmail: entries[section].contact_email,
            contactPhone: entries[section].contact_phone,
            managerId: parseInt(entries[section].manager_id, 10),
          }),
        });
        if (response.ok) {
          console.log(`Hotel with ID ${editEntries.id} updated successfully.`);
          setEditEntries({ id: '', newEntry: '' }); // Clear input
        } else {
          console.error(`Failed to update hotel with ID ${editEntries.id}`);
        }
      } catch (error) {
        console.error('Error updating hotel:', error);
      }
    }
    if (section === 'employees') {
      try {
        const response = await fetch(`http://localhost:8080/api/employee/edit/${editEntries.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            employeeName: entries[section].employee_name,
            employeeAddress: entries[section].employee_address,
            SIN: entries[section].SIN,
            position: entries[section].employee_position,
          }),
        });
        if (response.ok) {
          console.log(`Employee with ID ${editEntries.id} updated successfully.`);
          setEditEntries({ id: '', newEntry: '' }); // Clear input
        } else {
          console.error(`Failed to update employee with ID ${editEntries.id}`);
        }
      } catch (error) {
        console.error('Error updating employee:', error);
      }
    }
  };

  return (
    <div className="admin-page">
      <header className="top-nav">
        <div className="logo">MyHotel ADMIN</div>
        <div className="sql-buttons1">
          <Link to="/view1">SQL View 1</Link>
        </div>
        <div className="sql-buttons2">
          <Link to="/view2">SQL View 2</Link>
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
                  <input type="text" placeholder="ID Number" onChange={(e) => handleChange(e, section, 'id_number')} />
                </>
              )}
              {section === 'employees' && (
                <>
                  <input type="number" placeholder="Employee ID" onChange={(e) => handleChange(e, section, 'employee_id')} />
                  <input type="text" placeholder="Employee Name" onChange={(e) => handleChange(e, section, 'employee_name')} />
                  <input type="text" placeholder="Employee Address" onChange={(e) => handleChange(e, section, 'employee_address')} />
                  <input type="number" placeholder="SIN" onChange={(e) => handleChange(e, section, 'SIN')} />
                  <input type="text" placeholder="Position" onChange={(e) => handleChange(e, section, 'employee_position')} />
                  <input type="number" placeholder="Hotel ID" onChange={(e) => handleChange(e, section, 'hotel_id')} />
                </>
              )}
              {section === 'hotels' && (
                <>
                  <input type="text" placeholder="Hotel ID" onChange={(e) => handleEditChange(e, section, 'hotel_id')} />
                  <input type="number" placeholder="Chain ID" onChange={(e) => handleChange(e, section, 'chain_id')} />
                  <input type="text" placeholder="Hotel Name" onChange={(e) => handleChange(e, section, 'hotel_name')} />
                  <input type="number" placeholder="Rating" onChange={(e) => handleChange(e, section, 'rating')} />
                  <input type="text" placeholder="Hotel Address" onChange={(e) => handleChange(e, section, 'hotel_address')} />
                  <input type="text" placeholder="City" onChange={(e) => handleChange(e, section, 'city')} />
                  <input type="text" placeholder="State" onChange={(e) => handleChange(e, section, 'state')} />
                  <input type="text" placeholder="Amount of Rooms" onChange={(e) => handleChange(e, section, 'amount_of_rooms')} />
                  <input type="text" placeholder="Contact Email" onChange={(e) => handleChange(e, section, 'contact_email')} />
                  <input type="text" placeholder="Contact Phone Number" onChange={(e) => handleChange(e, section, 'contact_phone')} />
                  <input type="number" placeholder="Manager ID" onChange={(e) => handleChange(e, section, 'manager_id')} />
                </>
              )}
              {section === 'rooms' && (
                <>
                  <input type="text" placeholder="Room ID" onChange={(e) => handleEditChange(e, section, 'room_id')} />
                  <input type="text" placeholder="Hotel ID" onChange={(e) => handleChange(e, section, 'hotel_id')} />
                  <input type="number" placeholder="Price" onChange={(e) => handleChange(e, section, 'price')} />
                  <input type="text" placeholder="Room View" onChange={(e) => handleChange(e, section, 'view')} />
                  <input type="text" placeholder="Amenities" onChange={(e) => handleChange(e, section, 'amentities')} />
                  <input type="number" placeholder="Capacity" onChange={(e) => handleChange(e, section, 'capacity')} />
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
                  <input type="text" placeholder="ID Number" onChange={(e) => handleChange(e, section, 'id_number')} />
                  <input type="date" placeholder="Registration Date" onChange={(e) => handleChange(e, section, 'registration_date')} />
                </>
              )}
              {section === 'employees' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Employee Name" onChange={(e) => handleChange(e, section, 'employee_name')} />
                  <input type="text" placeholder="Employee Address" onChange={(e) => handleChange(e, section, 'employee_address')} />
                  <input type="number" placeholder="SIN" onChange={(e) => handleChange(e, section, 'SIN')} />
                  <input type="text" placeholder="Position" onChange={(e) => handleChange(e, section, 'employee_position')} />
                  <input type="number" placeholder="Hotel ID" onChange={(e) => handleChange(e, section, 'hotel_id')} />
                </>
              )}
              {section === 'hotels' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="number" placeholder="Chain ID" onChange={(e) => handleChange(e, section, 'chain_id')} />
                  <input type="text" placeholder="Hotel Name" onChange={(e) => handleChange(e, section, 'hotel_name')} />
                  <input type="number" placeholder="Rating" onChange={(e) => handleChange(e, section, 'rating')} />
                  <input type="text" placeholder="Hotel Address" onChange={(e) => handleChange(e, section, 'hotel_address')} />
                  <input type="text" placeholder="City" onChange={(e) => handleChange(e, section, 'city')} />
                  <input type="text" placeholder="State" onChange={(e) => handleChange(e, section, 'state')} />
                  <input type="text" placeholder="Amount of Rooms" onChange={(e) => handleChange(e, section, 'amount_of_rooms')} />
                  <input type="text" placeholder="Contact Email" onChange={(e) => handleChange(e, section, 'contact_email')} />
                  <input type="text" placeholder="Contact Phone Number" onChange={(e) => handleChange(e, section, 'contact_phone')} />
                  <input type="number" placeholder="Manager ID" onChange={(e) => handleChange(e, section, 'manager_id')} />
                </>
              )}
              {section === 'rooms' && (
                <>
                  <input type="text" placeholder={`Enter ${section} ID to edit`} value={editEntries.id} onChange={(e) => handleEditChange(e, 'id')} />
                  <input type="text" placeholder="Hotel ID" onChange={(e) => handleChange(e, section, 'hotel_id')} />
                  <input type="number" placeholder="Price" onChange={(e) => handleChange(e, section, 'price')} />
                  <input type="text" placeholder="Room View" onChange={(e) => handleChange(e, section, 'view')} />
                  <input type="text" placeholder="Amenities" onChange={(e) => handleChange(e, section, 'amentities')} />
                  <input type="number" placeholder="Capacity" onChange={(e) => handleChange(e, section, 'capacity')} />
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
