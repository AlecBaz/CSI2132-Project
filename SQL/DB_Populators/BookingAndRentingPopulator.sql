-- Insert a booking record into book_room for a room (e.g., room_id = 1)
INSERT INTO book_room (room_id) VALUES (1);

-- Insert the corresponding booking record (assume auto-generated booking_id = 1)
INSERT INTO booking (booking_id, customer_id, booking_date, checkin_date, checkout_date, status) VALUES
(1, 1, '2023-04-01', '2023-04-10', '2023-04-15', 'Booked');

-- Save the booking details in save_book
INSERT INTO save_book (booking_id, booking_date, checkin_date, checkout_date) VALUES
(1, '2023-04-01', '2023-04-10', '2023-04-15');

-- Insert a renting record into rent_room for the same room (room_id = 1)
INSERT INTO rent_room (room_id) VALUES (1);

-- Insert the renting details (assume auto-generated renting_id = 1)
INSERT INTO renting (renting_id, customer_id, start_date, end_date, payment_status) VALUES
(1, 1, '2023-04-10', '2023-04-15', 'Paid');

-- Save the renting details in save_rent
INSERT INTO save_rent (renting_id, start_date, end_date) VALUES
(1, '2023-04-10', '2023-04-15');

-- Finally, archive the booking and renting details
INSERT INTO archive (booking_id, renting_id, checkin_date, checkout_date, booking_date, start_date, end_date) VALUES
(1, 1, '2023-04-10', '2023-04-15', '2023-04-01', '2023-04-10', '2023-04-15');
