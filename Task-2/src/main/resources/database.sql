CREATE DATABASE IF NOT EXISTS ironlady_automation;
USE ironlady_automation;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- In real app, hash this
    role VARCHAR(20) NOT NULL DEFAULT 'STAFF'
);

CREATE TABLE IF NOT EXISTS programs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    duration VARCHAR(50),
    fee DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS learners (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    enrollment_date DATE,
    program_id INT,
    FOREIGN KEY (program_id) REFERENCES programs(id) ON DELETE SET NULL
);

-- Seed data
INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');
INSERT INTO programs (name, description, duration, fee) VALUES 
('Java Full Stack', 'Comprehensive Java course', '6 Months', 50000.00),
('Data Science', 'AI and ML fundamentals', '4 Months', 60000.00);
