INSERT INTO customer (
    customer_id,
    id_type,
    customer_address,
    customer_name,
    registration_date,
    id_number
  )
VALUES (
    customer_id:int,
    'id_type:varchar',
    'customer_address:varchar',
    'customer_name:varchar',
    'registration_date:date',
    'id_number:varchar'
  );INSERT INTO customer (id_type, customer_address, customer_name, registration_date) VALUES
('SSN', '100 Main St, Beverly Hills, CA', 'John Doe', '2023-01-10'),
('Driving License', '200 Oak St, Austin, TX', 'Jane Smith', '2023-02-15'),
('Passport', '300 Pine Ave, Orlando, FL', 'Mike Johnson', '2023-03-20');