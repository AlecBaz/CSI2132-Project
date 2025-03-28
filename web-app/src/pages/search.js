import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Using Link for navigation
import '../styles/search.css';

function Search() {
  const [criteria, setCriteria] = useState({
    startDate: '',
    endDate: '',
    chain: '',
    maxPrice: '',
    minPrice: '',
    capacity: '',
    city: '',
    state: '',
    rating: ''
  });
  

  const [availableRooms, setAvailableRooms] = useState([]);
  const [chainNames, setChainNames] = useState([]);
  const navigate = useNavigate(); // ✅ Initialize navigate function

  // ✅ 2nd useEffect for fetching chain names
  useEffect(() => {
    fetch('http://localhost:8080/api/chains/names')
      .then(res => res.json())
      .then(data => setChainNames(data))
      .catch(err => console.error('Error fetching chain names:', err));
  }, []);

  useEffect(() => {
    const debounceTimeout = setTimeout(() => {
      if (
        (criteria.startDate && criteria.endDate) || 
        criteria.chain || 
        criteria.maxPrice || 
        criteria.minPrice || 
        criteria.capacity || 
        criteria.city || 
        criteria.state || 
        criteria.rating
      ) {
        const today = new Date().toISOString().split('T')[0];
        const query = new URLSearchParams({
          startDate: criteria.startDate || today,
          endDate: criteria.endDate || '9999-12-31',
          chain: criteria.chain,
          maxPrice: criteria.maxPrice || 9999,
          minPrice: criteria.minPrice || 0,
          capacity: criteria.capacity || 1,
          city: criteria.city,
          state: criteria.state,
          rating: criteria.rating || 1
        }).toString();
    
        fetch(`http://localhost:8080/api/rooms/search/filter?${query}`)
          .then(res => res.json())
          .then(data => setAvailableRooms(data))
          .catch(err => console.error("Error fetching filtered rooms:", err));
      }
    }, 1000); // ⏱ 500ms debounce delay
  
    return () => clearTimeout(debounceTimeout); // ❌ Clear timeout if user types again
  }, [criteria]);
  
  const handleBookRoom = (roomId) => {
    navigate(`/booking/${roomId}`); // Redirect to booking page with roomID
  };
    
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCriteria(prev => ({ ...prev, [name]: value }));
  };

  return (
    <div className="app-container">
      {/* Top Task Bar */}
      <header className="top-nav">
        <div className="logo">MyHotel</div>
         {/* New Admin Button on the far right */}
         <div className="admin-link">
          <Link to="/admin">Admin</Link>
        </div>
        <div className="employee-link">
          <Link to="/employee">Employee</Link>
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
              <label htmlFor="chain">Hotel Chain:</label>
              <select
                id="chain"
                name="chain"
                value={criteria.chain}
                onChange={handleInputChange}
              >
                <option value="">Select a chain</option>
                {chainNames.map((name, index) => (
                  <option key={index} value={name}>{name}</option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="minPrice">Min Price:</label>
              <input
                type="number"
                id="minPrice"
                name="minPrice"
                value={criteria.minPrice}
                onChange={handleInputChange}
                placeholder="e.g. 50"
              />
            </div>

            <div className="form-group">
              <label htmlFor="maxPrice">Max Price:</label>
              <input
                type="number"
                id="maxPrice"
                name="maxPrice"
                value={criteria.maxPrice}
                onChange={handleInputChange}
                placeholder="e.g. 200"
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
              <label htmlFor="city">City:</label>
              <input
                type="text"
                id="city"
                name="city"
                value={criteria.city}
                onChange={handleInputChange}
                placeholder="e.g. Ottawa"
              />
            </div>

            <div className="form-group">
              <label htmlFor="state">State:</label>
              <input
                type="text"
                id="state"
                name="state"
                value={criteria.state}
                onChange={handleInputChange}
                placeholder="e.g. Ontario"
              />
            </div>

            <div className="form-group">
              <label htmlFor="rating">Hotel Rating (1-5):</label>
              <input
                type="number"
                id="rating"
                name="rating"
                min="1"
                max="5"
                value={criteria.rating}
                onChange={handleInputChange}
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
                <div key={room.roomId} className="room-box">
                  <h3>{room.hotelName}</h3>
                  <p>{room.hotelAddress}</p>
                  <h3>Room {room.roomId}</h3>
                  <p>Capacity: {room.capacity}</p>
                  <p>Price: ${room.price}</p>
                  <p>View: {room.view}</p>
                  <p>Amenities: {room.amentities}</p>
                  <p>Extendable: {room.extendable ? 'Yes' : 'No'}</p>
                  <p>Damages: {room.damages || 'None'}</p>
                  <button className="book-btn" onClick={() => handleBookRoom(room.roomId)}>Book</button>
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
