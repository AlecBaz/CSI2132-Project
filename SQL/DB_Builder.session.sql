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
CREATE TABLE book_room(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(room_id)
)

-- @block
CREATE TABLE booking(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (booking_id) REFERENCES book_room(booking_id),
    booking_date DATE,
    checkin_date DATE,
    checkout_date DATE,
    status VARCHAR(255)
)

-- @block
ALTER TABLE booking
ADD COLUMN room_id INT NOT NULL;

-- @block
ALTER TABLE booking
ADD CONSTRAINT fk_booking_room
FOREIGN KEY (room_id) REFERENCES room(room_id);

-- @block
CREATE TABLE save_book(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY (booking_id) REFERENCES book_room(booking_id),
    booking_date DATE REFERENCES booking(booking_date),
    checkin_date DATE REFERENCES booking(checkin_date),
    checkout_date DATE REFERENCES booking(checkout_date)
)

-- @block
CREATE TABLE rent_room(
    renting_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(room_id)
)

-- @block
CREATE TABLE renting(
    renting_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (renting_id) REFERENCES rent_room(renting_id),
    start_date DATE,
    end_date DATE,
    payment_status VARCHAR(255)
)

-- @block
ALTER TABLE renting
ADD COLUMN room_id INT NOT NULL;

-- @block
ALTER TABLE renting
ADD CONSTRAINT fk_renting_room
FOREIGN KEY (room_id) REFERENCES room(room_id);

-- @block
CREATE TABLE save_rent(
    renting_id INT PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY (renting_id) REFERENCES rent_room(renting_id),
    start_date DATE REFERENCES renting(start_date),
    end_date DATE REFERENCES renting(end_date)
)

-- @block
CREATE TABLE archive(
    archive_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    renting_id INT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES save_book(booking_id),
    FOREIGN KEY (renting_id) REFERENCES save_rent(renting_id),
    checkin_date DATE REFERENCES save_book(checkin_date),
    checkout_date DATE REFERENCES save_book(checkout_date),
    booking_date DATE REFERENCES save_book(booking_date),
    start_date DATE REFERENCES save_rent(start_date),
    end_date DATE REFERENCES save_rent(end_date)
)