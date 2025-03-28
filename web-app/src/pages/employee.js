import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/employee.css';

function Employee() {
  const [bookingId, setBookingId] = useState('');
  const [rentalInfo, setRentalInfo] = useState({ roomId: '', customerId: '', startDate: '', endDate: '' });
  const [paymentInfo, setPaymentInfo] = useState({ rentalId: '', amount: '' });

  // Convert Booking to Renting
  const handleConfirmBooking = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/renting/convert', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        // Sending the bookingId as a number in the request body
        body: JSON.stringify(parseInt(bookingId, 10))
      });
      if (!response.ok) {
        throw new Error('Error confirming booking');
      }
      console.log(`Booking converted successfully: ${bookingId}`);
    } catch (error) {
      console.error(error);
    }
  };

  // Direct Renting (changed to POST for sending JSON)
  const handleDirectRenting = async () => {
    try {
      const rentingData = {
        customerId: rentalInfo.customerId,
        roomId: parseInt(rentalInfo.roomId, 10),
        startDate: rentalInfo.startDate,
        endDate: rentalInfo.endDate
      };
      const response = await fetch('http://localhost:8080/api/renting/direct', {
        method: 'POST', // Use POST so we can include a JSON body
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(rentingData)
      });
      if (!response.ok) {
        throw new Error('Error processing direct renting');
      }
      console.log(`Direct renting processed successfully for Room: ${rentalInfo.roomId}`);
    } catch (error) {
      console.error(error);
    }
  };

  // Insert Customer Payment using GET with query parameters
  const handlePayment = async () => {
    try {
      const url = `http://localhost:8080/api/renting/processpayment?rentingId=${encodeURIComponent(paymentInfo.rentalId)}&amount=${encodeURIComponent(paymentInfo.amount)}`;
      const response = await fetch(url, {
        method: 'GET'
      });
      if (!response.ok) {
        throw new Error('Error processing payment');
      }
      console.log(`Payment processed successfully for Rental ID: ${paymentInfo.rentalId}`);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="employee-page">
      <header className="employee-header">
        <h1>MyHotel Employee Panel</h1>
      </header>

      <div className="employee-content">
        {/* Convert Booking to Renting */}
        <div className="employee-section">
          <h3>Convert Booking to Renting</h3>
          <input
            type="text"
            placeholder="Enter Booking ID"
            value={bookingId}
            onChange={(e) => setBookingId(e.target.value)}
          />
          <button onClick={handleConfirmBooking}>Confirm Check-in</button>
        </div>

        {/* Direct Renting */}
        <div className="employee-section">
          <h3>Direct Renting</h3>
          <input
            type="text"
            placeholder="Room ID"
            value={rentalInfo.roomId}
            onChange={(e) => setRentalInfo({ ...rentalInfo, roomId: e.target.value })}
          />
          <input
            type="text"
            placeholder="Customer ID"
            value={rentalInfo.customerId}
            onChange={(e) => setRentalInfo({ ...rentalInfo, customerId: e.target.value })}
          />
          <input
            type="date"
            placeholder="Start Date"
            value={rentalInfo.startDate}
            onChange={(e) => setRentalInfo({ ...rentalInfo, startDate: e.target.value })}
          />
          <input
            type="date"
            placeholder="End Date"
            value={rentalInfo.endDate}
            onChange={(e) => setRentalInfo({ ...rentalInfo, endDate: e.target.value })}
          />
          <button onClick={handleDirectRenting}>Confirm Renting</button>
        </div>

        {/* Insert Payment */}
        <div className="employee-section">
          <h3>Insert Customer Payment</h3>
          <input
            type="text"
            placeholder="Rental ID"
            value={paymentInfo.rentalId}
            onChange={(e) => setPaymentInfo({ ...paymentInfo, rentalId: e.target.value })}
          />
          <input
            type="text"
            placeholder="Payment Amount"
            value={paymentInfo.amount}
            onChange={(e) => setPaymentInfo({ ...paymentInfo, amount: e.target.value })}
          />
          <button onClick={handlePayment}>Process Payment</button>
        </div>
      </div>

      <footer className="employee-footer">
        <Link to="/">Back to Home</Link>
      </footer>
    </div>
  );
}

export default Employee;
