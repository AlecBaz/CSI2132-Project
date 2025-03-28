import React, { useState, useEffect } from "react";
import "../styles/view1.css";

const View1 = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/available-rooms")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                setRooms(data);
                setLoading(false);
            })
            .catch(error => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    return (
        <div className="view1-container">
            <h2>Available Rooms Per Area</h2>
            {loading && <p>Loading...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && rooms.length === 0 && <p>No data available.</p>}

            {!loading && !error && rooms.length > 0 && (
                <table className="view1-table">
                    <thead>
                        <tr>
                            <th>City</th>
                            <th>State</th>
                            <th>Available Rooms</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rooms.map((room, index) => (
                            <tr key={index}>
                                <td>{room.city}</td>
                                <td>{room.state}</td>
                                <td>{room.availableRooms}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default View1;
