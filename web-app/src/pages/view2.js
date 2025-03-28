import React, { useState, useEffect } from "react";
import "../styles/view2.css";

const View2 = () => {
    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/hotel-room-capacity")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                setHotels(data);
                setLoading(false);
            })
            .catch(error => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    return (
        <div className="view2-container">
            <h2>Aggregated Room Capacity Per Hotel</h2>
            {loading && <p>Loading...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && hotels.length === 0 && <p>No data available.</p>}

            {!loading && !error && hotels.length > 0 && (
                <table className="view2-table">
                    <thead>
                        <tr>
                            <th>Hotel Name</th>
                            <th>Total Room Capacity</th>
                        </tr>
                    </thead>
                    <tbody>
                        {hotels.map((hotel, index) => (
                            <tr key={index}>
                                <td>{hotel.hotelName}</td>
                                <td>{hotel.totalCapacity}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default View2;
