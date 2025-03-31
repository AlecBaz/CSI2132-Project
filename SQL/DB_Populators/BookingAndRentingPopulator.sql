INSERT INTO booking(room_id, customer_id, booking_date, checkin_date, checkout_date, status) VALUES
(2, 1, '2025-03-24', '2025-04-10', '2025-04-15', 'CONFRIMED'),
(8, 2, '2025-03-25', '2025-05-01', '2025-05-05', 'CONFIRMED'),
(9, 3, '2025-03-26', '2025-04-20', '2025-04-25', 'CONFIRMED'),
(1, 1, '2025-03-31', '2025-03-31', '2025-04-04', 'DIRECT_RENT'),
(9, 2, '2025-03-31', '2025-03-31', '2025-04-05', 'DIRECT_RENT'),
(15, 3, '2025-03-31', '2025-03-31', '2025-04-06', 'DIRECT_RENT');

INSERT INTO renting(room_id, customer_id, start_date, end_date, payment_status) VALUES
(1, 1, '2025-03-31', '2025-04-04', 'Pending'),
(9, 2, '2025-03-31', '2025-04-05', 'Pending'),
(15, 3, '2025-03-31', '2025-04-06', 'Pending');