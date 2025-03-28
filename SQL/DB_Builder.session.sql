-- @block
CREATE TABLE hotel_chain(
    chain_id INT PRIMARY KEY AUTO_INCREMENT,
    chain_name VARCHAR(255),
    chain_address VARCHAR(255),
    number_of_hotels INT,
    email_addresses TEXT,
    phone_numbers TEXT
)

-- @block
CREATE TABLE hotel(
    hotel_id INT PRIMARY KEY AUTO_INCREMENT,
    chain_id INT NOT NULL,
    FOREIGN KEY (chain_id) REFERENCES hotel_chain(chain_id),
    hotel_name VARCHAR(255),
    rating INT,
    hotel_address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    amount_of_rooms INT,
    contact_email VARCHAR(255) REFERENCES hotel_chain(email_addresses),
    contact_phone VARCHAR(255) REFERENCES hotel_chain(phone_numbers),
    manager_id INT
)

-- @block
CREATE TABLE employee(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_id INT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    employee_name VARCHAR(255),
    employee_address VARCHAR(255),
    SIN INT,
    employee_position VARCHAR(255)
)

-- @block
CREATE TABLE room(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_id INT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    price DECIMAL(10,2),
    view VARCHAR(255),
    amentities TEXT,
    extendable BOOLEAN,
    capacity INT,
    damages TEXT
)

-- @block
CREATE TABLE customer(
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    id_type VARCHAR(255),
    customer_address VARCHAR(255),
    customer_name VARCHAR(255),
    registration_date DATE
)

-- @block
CREATE TABLE booking(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    booking_date DATE,
    checkin_date DATE,
    checkout_date DATE,
    status VARCHAR(255)
)

-- @block
CREATE TABLE renting(
    renting_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    start_date DATE,
    end_date DATE,
    payment_status VARCHAR(255)
)

-- @block
CREATE TABLE archive(
    archive_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    renting_id INT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id),
    FOREIGN KEY (renting_id) REFERENCES renting(renting_id),
    checkin_date DATE REFERENCES booking(checkin_date),
    checkout_date DATE REFERENCES booking(checkout_date),
    booking_date DATE REFERENCES booking(booking_date),
    start_date DATE REFERENCES renting(start_date),
    end_date DATE REFERENCES renting(end_date)
)

-- View 1: Available Rooms Per Area
-- @block
CREATE VIEW AvailableRoomsPerArea AS
SELECT 
    h.city,
    h.state,
    COUNT(r.room_id) AS available_rooms
FROM hotel h
JOIN room r ON h.hotel_id = r.hotel_id
LEFT JOIN booking b ON r.room_id = b.room_id AND b.status = 'booked'
LEFT JOIN renting rent ON r.room_id = rent.room_id
WHERE b.room_id IS NULL AND rent.room_id IS NULL
GROUP BY h.city, h.state;

-- View 2: Hotel Room Capacity
-- @block
CREATE VIEW HotelRoomCapacity AS
SELECT 
    h.hotel_id,
    h.hotel_name,
    SUM(r.capacity) AS total_capacity
FROM hotel h
JOIN room r ON h.hotel_id = r.hotel_id
GROUP BY h.hotel_id, h.hotel_name;


-- @block
DROP TABLE IF EXISTS archive;
DROP TABLE IF EXISTS save_book;
DROP TABLE IF EXISTS save_rent;
DROP TABLE IF EXISTS renting;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS rent_room;
DROP TABLE IF EXISTS book_room;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS hotel_chain;