import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/employee.css';

function Employee() {
  const [bookingId, setBookingId] = useState('');
  const [rentalInfo, setRentalInfo] = useState({ roomId: '', customerId: '', duration: '' });
  const [paymentInfo, setPaymentInfo] = useState({ rentalId: '', amount: '' });

  const handleConfirmBooking = () => {
    console.log(`Confirming booking: ${bookingId}`);
    // API call to confirm booking and convert it into a rental
  };

  const handleDirectRenting = () => {
    console.log(`Direct Renting - Room: ${rentalInfo.roomId}, Customer: ${rentalInfo.customerId}, Duration: ${rentalInfo.duration}`);
    // API call to process a direct renting
  };

  const handlePayment = () => {
    console.log(`Processing payment for rental: ${paymentInfo.rentalId}, Amount: ${paymentInfo.amount}`);
    // API call to insert a customer payment
  };

  return (
    <div className="employee-page">
      <header className="employee-header">
        <h1>Hotel Employee Panel</h1>
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
            type="text"
            placeholder="Duration (in days)"
            value={rentalInfo.duration}
            onChange={(e) => setRentalInfo({ ...rentalInfo, duration: e.target.value })}
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
