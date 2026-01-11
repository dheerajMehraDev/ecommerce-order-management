INSERT INTO users (name, email, password, created_at, updated_at) VALUES

-- 🔹 ADMIN / SYSTEM USERS
('Admin User', 'admin@example.com', 'admin123', NOW(), NOW()),
('Super Admin', 'superadmin@example.com', 'super123', NOW(), NOW()),

-- 🔹 NORMAL CUSTOMERS
('John Doe', 'john@example.com', 'john123', NOW(), NOW()),
('Jane Smith', 'jane@example.com', 'jane123', NOW(), NOW()),
('Mike Ross', 'mike@example.com', 'mike123', NOW(), NOW()),

-- 🔹 GUEST / TEMP USERS
('Guest User', 'guest1@example.com', 'guest123', NOW(), NOW()),
('Temp User', 'temp@example.com', 'temp123', NOW(), NOW()),

-- 🔹 EDGE / TEST CASE USERS
('SingleName', 'singlename@example.com', 'single123', NOW(), NOW()),
('Long Name User For Testing', 'longnameuser@example.com', 'long123', NOW(), NOW()),

-- 🔹 OLDER USERS (date variation)
('Old User 1', 'old1@example.com', 'old123',
 DATE_SUB(NOW(), INTERVAL 30 DAY),
 DATE_SUB(NOW(), INTERVAL 30 DAY)),

('Old User 2', 'old2@example.com', 'old123',
 DATE_SUB(NOW(), INTERVAL 90 DAY),
 DATE_SUB(NOW(), INTERVAL 90 DAY));
