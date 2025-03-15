import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'; // Using Link for navigation
import '../styles/search.css';

function Search() {
  const [criteria, setCriteria] = useState({
    startDate: '',
    endDate: '',
    capacity: '',
    area: '',
    hotelChain: '',
    hotelCategory: '',
    totalRooms: '',
    price: ''
  });

  const [availableRooms, setAvailableRooms] = useState([]);

  // Simulate fetching available rooms based on criteria
  useEffect(() => {
    const timeoutId = setTimeout(() => {
      // Replace this with your API call
      setAvailableRooms([
        { id: 1, roomNumber: '101', capacity: criteria.capacity || 2, price: criteria.price || 100 },
        { id: 2, roomNumber: '102', capacity: criteria.capacity || 2, price: criteria.price || 120 },
        { id: 3, roomNumber: '103', capacity: criteria.capacity || 3, price: criteria.price || 150 }
      ]);
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [criteria]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCriteria(prev => ({ ...prev, [name]: value }));
  };

  return (
    <div className="app-container">
      {/* Top Task Bar */}
      <header className="top-nav">
        <div className="logo">MyHotel</div>
        <nav className="nav-menu">
          <div className="nav-item">
            <span>Rooms</span>
            <div className="dropdown">
              <a href="#rooms">All Rooms</a>
              <a href="#luxury">Luxury</a>
              <a href="#budget">Budget</a>
            </div>
          </div>
          <div className="nav-item">
            <span>Hotels</span>
            <div className="dropdown">
              <a href="#chains">Hotel Chains</a>
              <a href="#boutique">Boutique</a>
            </div>
          </div>
          <div className="nav-item">
            <span>Offers</span>
            <div className="dropdown">
              <a href="#discounts">Discounts</a>
              <a href="#packages">Packages</a>
            </div>
          </div>
          <div className="nav-item">
            <span>Help</span>
            <div className="dropdown">
              <a href="#contact">Contact Us</a>
              <a href="#faq">FAQ</a>
            </div>
          </div>
        </nav>
         {/* New Admin Button on the far right */}
         <div className="admin-link">
          <Link to="/admin">Admin</Link>
        </div>
      </header>

      <div className="content-container">
        {/* Left Sidebar: Search Form */}
        <aside className="search-sidebar">
          <h2>Search Rooms</h2>
          <form>
            <div className="form-group">
              <label htmlFor="startDate">Start Date:</label>
              <input 
                type="date" 
                id="startDate" 
                name="startDate" 
                value={criteria.startDate} 
                onChange={handleInputChange} 
              />
            </div>
            <div className="form-group">
              <label htmlFor="endDate">End Date:</label>
              <input 
                type="date" 
                id="endDate" 
                name="endDate" 
                value={criteria.endDate} 
                onChange={handleInputChange} 
              />
            </div>
            <div className="form-group">
              <label htmlFor="capacity">Room Capacity:</label>
              <input 
                type="number" 
                id="capacity" 
                name="capacity" 
                value={criteria.capacity} 
                onChange={handleInputChange} 
                placeholder="e.g. 2" 
              />
            </div>
            <div className="form-group">
              <label htmlFor="area">Area:</label>
              <input 
                type="text" 
                id="area" 
                name="area" 
                value={criteria.area} 
                onChange={handleInputChange} 
                placeholder="e.g. Downtown" 
              />
            </div>
            <div className="form-group">
              <label htmlFor="hotelChain">Hotel Chain:</label>
              <select 
                id="hotelChain" 
                name="hotelChain" 
                value={criteria.hotelChain} 
                onChange={handleInputChange}
              >
                <option value="">Select a chain</option>
                <option value="Chain A">Chain A</option>
                <option value="Chain B">Chain B</option>
                <option value="Chain C">Chain C</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="hotelCategory">Hotel Category:</label>
              <select 
                id="hotelCategory" 
                name="hotelCategory" 
                value={criteria.hotelCategory} 
                onChange={handleInputChange}
              >
                <option value="">Select a category</option>
                <option value="Luxury">Luxury</option>
                <option value="Budget">Budget</option>
                <option value="Boutique">Boutique</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="totalRooms">Total Number of Rooms:</label>
              <input 
                type="number" 
                id="totalRooms" 
                name="totalRooms" 
                value={criteria.totalRooms} 
                onChange={handleInputChange} 
                placeholder="e.g. 50" 
              />
            </div>
            <div className="form-group">
              <label htmlFor="price">Price:</label>
              <input 
                type="number" 
                id="price" 
                name="price" 
                value={criteria.price} 
                onChange={handleInputChange} 
                placeholder="e.g. 100" 
              />
            </div>
          </form>
        </aside>

        {/* Main Content: Room Listings */}
        <main className="room-listings">
          <h2>Available Rooms</h2>
          <div className="rooms-grid">
            {availableRooms.length > 0 ? (
              availableRooms.map(room => (
                <div key={room.id} className="room-box">
                  <h3>Room {room.roomNumber}</h3>
                  <p>Capacity: {room.capacity}</p>
                  <p>Price: ${room.price}</p>
                  <button className="book-btn">Book / Rent</button>
                </div>
              ))
            ) : (
              <p>No rooms available with current criteria.</p>
            )}
          </div>
        </main>
      </div>
    </div>
  );
}

export default Search;
