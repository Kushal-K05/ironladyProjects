<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iron Lady Smart Assistant</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="chat-container">
        <div class="chat-header">
            <h1>Iron Lady Smart Guide</h1>
        </div>
        <div class="chat-box" id="chat-box">
            <div class="message bot-message">
                Hello! I am your Iron Lady Smart Assistant. Ask me about our programs, eligibility, or fees!
            </div>
        </div>
        <div class="input-area">
            <input type="text" id="user-input" placeholder="Type your message here..." autocomplete="off">
            <button onclick="sendMessage()">Send</button>
        </div>
    </div>
    <script src="js/script.js"></script>
</body>
</html>
