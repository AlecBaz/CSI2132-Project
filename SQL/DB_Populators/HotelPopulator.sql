INSERT INTO hotel (chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES
(1, 'Luxury Stay Downtown', 5, '100 Downtown Blvd, Beverly Hills, CA', 'Beverly Hills', 'CA', 50, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay Uptown', 4, '200 Uptown Rd, Beverly Hills, CA', 'Beverly Hills', 'CA', 40, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay Midtown', 5, '300 Downtown Ave, Beverly Hills, CA', 'Beverly Hills', 'CA', 60, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay West', 3, '400 West St, Beverly Hills, CA', 'Beverly Hills', 'CA', 30, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay East', 4, '500 East Rd, Beverly Hills, CA', 'Beverly Hills', 'CA', 35, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay Suburb', 3, '600 Suburb Ln, Beverly Hills, CA', 'Beverly Hills', 'CA', 25, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay Central', 5, '700 Central Park, Beverly Hills, CA', 'Beverly Hills', 'CA', 45, 'contact@luxurystay.com', '123-456-7890', NULL),
(1, 'Luxury Stay Plaza', 4, '800 Plaza Ave, Beverly Hills, CA', 'Beverly Hills', 'CA', 55, 'contact@luxurystay.com', '123-456-7890', NULL);

-- Hotels for "Budget Inn" (chain_id = 2)
INSERT INTO hotel (chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES
(2, 'Budget Inn Central', 3, '101 Central St, Austin, TX', 'Austin', 'TX', 80, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn Downtown', 4, '202 Downtown Ave, Austin, TX', 'Austin', 'TX', 90, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn East', 3, '303 East Rd, Austin, TX', 'Austin', 'TX', 70, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn West', 4, '404 West Blvd, Austin, TX', 'Austin', 'TX', 75, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn North', 3, '505 North Ln, Austin, TX', 'Austin', 'TX', 65, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn South', 4, '606 South St, Austin, TX', 'Austin', 'TX', 85, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn Downtown 2', 3, '707 Downtown Rd, Austin, TX', 'Austin', 'TX', 80, 'info@budgetinn.com', '234-567-8901', NULL),
(2, 'Budget Inn Suburban', 3, '808 Suburban Ave, Austin, TX', 'Austin', 'TX', 60, 'info@budgetinn.com', '234-567-8901', NULL);

-- Hotels for "Comfort Suites" (chain_id = 3)
INSERT INTO hotel (chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES
(3, 'Comfort Suites City Center', 4, '111 City Center Rd, Orlando, FL', 'Orlando', 'FL', 40, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Beachside', 5, '222 Beachside Ave, Orlando, FL', 'Orlando', 'FL', 50, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Downtown', 4, '333 Downtown Blvd, Orlando, FL', 'Orlando', 'FL', 45, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Uptown', 3, '444 Uptown St, Orlando, FL', 'Orlando', 'FL', 35, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Garden', 4, '555 Garden Rd, Orlando, FL', 'Orlando', 'FL', 30, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Riverside', 5, '666 Riverside Dr, Orlando, FL', 'Orlando', 'FL', 55, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Downtown 2', 4, '777 Downtown Ln, Orlando, FL', 'Orlando', 'FL', 50, 'support@comfortsuites.com', '345-678-9012', NULL),
(3, 'Comfort Suites Suburban', 3, '888 Suburban Ave, Orlando, FL', 'Orlando', 'FL', 40, 'support@comfortsuites.com', '345-678-9012', NULL);
-- Hotels for "Elite Hotels" (chain_id = 4)
INSERT INTO hotel (chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES
(4, 'Elite Hotels Midtown', 5, '101 Midtown Ave, New York, NY', 'New York', 'NY', 60, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels Downtown', 4, '202 Downtown Blvd, New York, NY', 'New York', 'NY', 70, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels Uptown', 5, '303 Uptown St, New York, NY', 'New York', 'NY', 55, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels Central', 4, '404 Central Park West, New York, NY', 'New York', 'NY', 65, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels East', 3, '505 Eastside Ave, New York, NY', 'New York', 'NY', 50, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels West', 4, '606 West End, New York, NY', 'New York', 'NY', 60, 'service@elitehotels.com', '456-789-0123', NULL),
(4, 'Elite Hotels Downtown 2', 5, '707 Downtown Ln, New York, NY', 'New York', 'NY', 70, 'service@elitehotels.com', '456-789-0123', NULL),  -- duplicate Downtown area
(4, 'Elite Hotels Luxury', 5, '808 Luxury Blvd, New York, NY', 'New York', 'NY', 80, 'service@elitehotels.com', '456-789-0123', NULL);

-- Hotels for "Family Retreat" (chain_id = 5)
INSERT INTO hotel (chain_id, hotel_name, rating, hotel_address, city, state, amount_of_rooms, contact_email, contact_phone, manager_id) VALUES
(5, 'Family Retreat Seaside', 4, '111 Seaside Dr, San Diego, CA', 'San Diego', 'CA', 45, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Downtown', 5, '222 Downtown Ave, San Diego, CA', 'San Diego', 'CA', 50, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Uptown', 4, '333 Uptown Blvd, San Diego, CA', 'San Diego', 'CA', 40, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Central', 3, '444 Central St, San Diego, CA', 'San Diego', 'CA', 35, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Suburban', 4, '555 Suburban Rd, San Diego, CA', 'San Diego', 'CA', 30, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Harbor', 5, '666 Harbor Blvd, San Diego, CA', 'San Diego', 'CA', 55, 'hello@familyretreat.com', '567-890-1234', NULL),
(5, 'Family Retreat Downtown 2', 4, '777 Downtown Ln, San Diego, CA', 'San Diego', 'CA', 50, 'hello@familyretreat.com', '567-890-1234', NULL),  -- same Downtown area
(5, 'Family Retreat Vista', 3, '888 Vista Ave, San Diego, CA', 'San Diego', 'CA', 40, 'hello@familyretreat.com', '567-890-1234', NULL);
