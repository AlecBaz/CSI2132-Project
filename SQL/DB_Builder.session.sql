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
    SIN_num INT,
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
    registration_date DATE,
    id_number VARCHAR(255)
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
    booking_id INT,
    renting_id INT,
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
CREATE INDEX idx_room_filter ON room(hotel_id, price, capacity);
CREATE INDEX idx_hotel_filter ON hotel(chain_id, city, state, rating, amount_of_rooms);
CREATE INDEX idx_booking_date ON booking(checkin_date, checkout_date);


-- @block
CREATE TRIGGER after_booking_insert
AFTER INSERT ON booking
FOR EACH ROW
INSERT INTO archive (booking_id, checkin_date, checkout_date, booking_date)
VALUES (NEW.booking_id, NEW.checkin_date, NEW.checkout_date, NEW.booking_date);

-- @block
CREATE TRIGGER after_renting_insert
AFTER INSERT ON renting
FOR EACH ROW
INSERT INTO archive (renting_id, start_date, end_date)
VALUES (NEW.renting_id, NEW.start_date, NEW.end_date);

-- @block
-- hotel has at least one employee
ALTER TABLE employee
ADD CONSTRAINT chk_hotel_employee CHECK (hotel_id IS NOT NULL);

-- hotel has manager
ALTER TABLE hotel
ADD CONSTRAINT chk_hotel_manager CHECK (manager_id IS NOT NULL);

-- room belongs to hotel
ALTER TABLE room
ADD CONSTRAINT fk_room_hotel FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
ON DELETE CASCADE;

-- hotel belongs to chain
ALTER TABLE hotel
ADD CONSTRAINT fk_hotel_chain FOREIGN KEY (chain_id) REFERENCES hotel_chain(chain_id)
ON DELETE CASCADE;

-- room's price greater than 0
ALTER TABLE room
ADD CONSTRAINT chk_room_price CHECK (price > 0);

-- room's capacity at least 1
ALTER TABLE room
ADD CONSTRAINT chk_room_capacity CHECK (capacity >= 1);

-- check-in date is on/after the booking date
ALTER TABLE booking
ADD CONSTRAINT chk_checkin_after_booking CHECK (checkin_date >= booking_date);

-- check-out date is at least one day after check-in
ALTER TABLE booking
ADD CONSTRAINT chk_checkout_after_checkin CHECK (checkout_date > checkin_date);