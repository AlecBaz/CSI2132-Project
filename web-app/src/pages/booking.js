import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import '../styles/booking.css';

function Booking() {
  const { roomID } = useParams();
  const [roomInfo, setRoomInfo] = useState(null);
  const [existingCustomer, setExistingCustomer] = useState({ userID: '', startDate: '', endDate: '' });
  const [newCustomer, setNewCustomer] = useState({
    customerName: '',
    customerAddress: '',
    idType: '',
    idNumber: '',
    registrationDate: new Date().toISOString().split('T')[0],
    startDate: '',
    endDate: ''
  });

  useEffect(() => {
    fetch(`http://localhost:8080/api/rooms/search/roominfo/${roomID}`)
      .then(res => res.json())
      .then(data => setRoomInfo(data))
      .catch(err => console.error('Error fetching room info:', err));
  }, [roomID]);

  const handleExistingCustomerChange = (e) => {
    const { name, value } = e.target;
    setExistingCustomer(prev => ({ ...prev, [name]: value }));
  };

  const handleNewCustomerChange = (e) => {
    const { name, value } = e.target;
    setNewCustomer(prev => ({ ...prev, [name]: value }));
  };

  const confirmExistingBooking = async () => {
    const { userID, startDate, endDate } = existingCustomer;
    if (userID && startDate && endDate) {
      console.log('Existing customer booking:', existingCustomer);
      await addBooking(userID, startDate, endDate);
    } else {
      alert('Please fill out all fields for existing customer booking.');
    }
  };
  
  const confirmNewBooking = async () => {
    const { customerName, customerAddress, idType, idNumber, startDate, endDate } = newCustomer;
    if (customerName && customerAddress && idType && idNumber && startDate && endDate) {
      console.log('Registering new customer:', newCustomer);
      const customerId = await addCustomer();
      if (customerId) {
        await addBooking(customerId, startDate, endDate);
      }
    } else {
      alert('Please fill out all fields for new customer registration.');
    }
  };
  

  const addCustomer = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/customer/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          customerName: newCustomer.customerName,
          customerAddress: newCustomer.customerAddress,
          idType: newCustomer.idType,
          idNumber: newCustomer.idNumber,
        }),
      });
  
      if (!response.ok) {
        throw new Error('Failed to add customer');
      }
  
      console.log('Customer added successfully');
      return newCustomer.idNumber; // Use this as the customer ID
    } catch (error) {
      console.error('Error adding customer:', error);
      return null;
    }
  };
  
  const addBooking = async (customerId, startDate, endDate) => {
    try {
      const response = await fetch(`http://localhost:8080/api/booking/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          customerId,
          roomId: roomID,
          checkinDate: startDate,
          checkoutDate: endDate,
        }),
      });
  
      if (!response.ok) {
        throw new Error('Failed to add booking');
      }
  
      console.log('Booking added successfully');
    } catch (error) {
      console.error('Error adding booking:', error);
    }
  };
  
  return (
    <div className="booking-page">
      {/* Top Navigation Bar */}
      <header className="top-nav">
        <div className="logo">MyHotel</div>
      </header>

      <div className="booking-container">
        {/* Room Information Section */}
        <section className="room-info">
          <h2 className="section-title">Room Details</h2>
          <div className="section-content">
            {roomInfo ? (
              <>
                <p><strong>Hotel:</strong> {roomInfo.hotel.hotelName}</p>
                <p><strong>Room ID:</strong> {roomInfo.roomId}</p>
                <p><strong>Capacity:</strong> {roomInfo.capacity}</p>
                <p><strong>Price:</strong> ${roomInfo.price}</p>
                <p><strong>Available From:</strong> {roomInfo.availableFrom}</p>
                <p><strong>Available To:</strong> {roomInfo.availableTo}</p>
              </>
            ) : (
              <p>Loading room details...</p>
            )}
          </div>
        </section>

        {/* Existing Customer Booking Section */}
        <section className="existing-customer">
          <h2 className="section-title">Existing Customer</h2>
          <div className="section-content">
            <input type="text" name="userID" placeholder="User ID" value={existingCustomer.userID} onChange={handleExistingCustomerChange} />
            <input type="date" name="startDate" value={existingCustomer.startDate} onChange={handleExistingCustomerChange} />
            <input type="date" name="endDate" value={existingCustomer.endDate} onChange={handleExistingCustomerChange} />
            <button onClick={confirmExistingBooking}>Confirm Booking</button>
          </div>
        </section>

        {/* New Customer Registration Section */}
        <section className="new-customer">
          <h2 className="section-title">New Customer</h2>
          <div className="section-content">
            <input type="text" name="customerName" placeholder="Full Name" value={newCustomer.customerName} onChange={handleNewCustomerChange} />
            <input type="text" name="customerAddress" placeholder="Address" value={newCustomer.customerAddress} onChange={handleNewCustomerChange} />
            <input type="text" name="idType" placeholder="ID Type (e.g. SIN, License)" value={newCustomer.idType} onChange={handleNewCustomerChange} />
            <input type="text" name="idNumber" placeholder="ID Number" value={newCustomer.idNumber} onChange={handleNewCustomerChange} />
            <input type="date" name="startDate" value={newCustomer.startDate} onChange={handleNewCustomerChange} />
            <input type="date" name="endDate" value={newCustomer.endDate} onChange={handleNewCustomerChange} />
            <button onClick={confirmNewBooking}>Confirm Registration & Booking</button>
          </div>
        </section>
      </div>
    </div>
  );
}

export default Booking;