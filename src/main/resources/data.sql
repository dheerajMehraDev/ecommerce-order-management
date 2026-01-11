-- USERS
INSERT INTO users (name, email, password) VALUES
                                              ('Admin', 'admin@shop.com', 'admin123'),
                                              ('John', 'john@shop.com', 'john123');

INSERT INTO products
(name, description, price, quantity, category, active, created_at, updated_at)
VALUES

-- Electronics
('iPhone 15',
 'Latest Apple smartphone',
 80000.00,
 50,
 'ELECTRONICS',
 true,
 NOW(), NOW()),

('MacBook Air M2',
 'Apple laptop with M2 chip',
 120000.00,
 30,
 'ELECTRONICS',
 true,
 NOW(), NOW()),

-- Accessories
('Wireless Headphones',
 'Noise cancelling headphones',
 3000.00,
 100,
 'ACCESSORIES',
 true,
 NOW(), NOW()),

-- Clothing
('Men T-Shirt',
 'Cotton casual t-shirt',
 799.00,
 200,
 'CLOTHING',
 true,
 NOW(), NOW()),

('Women Jeans',
 'Slim fit denim jeans',
 1999.00,
 150,
 'CLOTHING',
 true,
 NOW(), NOW()),

-- Home Appliances
('Mixer Grinder',
 '750W mixer grinder',
 4500.00,
 40,
 'HOME_APPLIANCES',
 true,
 NOW(), NOW()),

-- Inactive / out-of-stock (edge cases)
('Old Model Phone',
 'Discontinued phone model',
 15000.00,
 0,
 'ELECTRONICS',
 false,
 NOW(), NOW());


