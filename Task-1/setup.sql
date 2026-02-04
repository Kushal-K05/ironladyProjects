CREATE DATABASE IF NOT EXISTS ironlady_db;
USE ironlady_db;

-- Table for storing program details
CREATE TABLE IF NOT EXISTS programs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    eligibility TEXT,
    duration VARCHAR(100),
    fees DECIMAL(10,2),
    certification VARCHAR(255)
);

-- Table for storing AI rules (keywords and responses)
CREATE TABLE IF NOT EXISTS chatbot_rules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    keyword VARCHAR(100) NOT NULL,
    response_text TEXT NOT NULL,
    category VARCHAR(50) -- e.g., 'program', 'general', 'support'
);

-- Table for storing user interactions
CREATE TABLE IF NOT EXISTS interactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_query TEXT,
    bot_response TEXT,
    interaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed Data: Programs
INSERT INTO programs (name, description, eligibility, duration, fees, certification) VALUES 
('Iron Lady Leadership Program', 'A flagship program to empower women leaders.', 'Women with 5+ years experience', '6 Months', 50000.00, 'Certified Iron Lady Leader'),
('Young Entrepreneur Bootcamp', 'Startup training for young aspiring entrepreneurs.', 'Age 18-25', '3 Months', 25000.00, 'Entrepreneurship Certificate'),
('Corporate Excellence', 'Upskilling for corporate professionals.', 'Graduates', '4 Months', 35000.00, 'Corporate Excellence Award');

-- Seed Data: Chatbot Rules
INSERT INTO chatbot_rules (keyword, response_text, category) VALUES 
('hello', 'Hello! Welcome to Iron Lady. How can I assist you today?', 'general'),
('hi', 'Hi there! I am your Iron Lady Smart Assistant. Ask me about our programs.', 'general'),
('programs', 'We offer several programs like the Iron Lady Leadership Program and Young Entrepreneur Bootcamp. Which one are you interested in?', 'program'),
('leadership', 'The Iron Lady Leadership Program is designed for experienced women professionals. It is a 6-month course.', 'program'),
('eligibility', 'Eligibility varies by program. For Leadership, it is 5+ years experience. For Bootcamp, it is age 18-25.', 'program'),
('fees', 'Our program fees range from 25,000 to 50,000 INR depending on the course. Please specify a program for details.', 'program'),
('contact', 'You can reach our support team at support@ironlady.com.', 'support'),
('enroll', 'To enroll, please visit our enrollment page at ironlady.com/enroll or click the "Apply Now" button.', 'general');
