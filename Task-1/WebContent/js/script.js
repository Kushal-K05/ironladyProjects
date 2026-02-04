function sendMessage() {
    const inputField = document.getElementById("user-input");
    const chatBox = document.getElementById("chat-box");
    const message = inputField.value.trim();

    if (message === "") return;

    // Add User Message
    appendMessage(message, "user-message");
    inputField.value = "";

    // Show Loading
    // In a real premium app, we might add a 'typing...' indicator here

    // Send Request to Backend
    fetch("chat", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "message=" + encodeURIComponent(message)
    })
    .then(response => response.json())
    .then(data => {
        appendMessage(data.reply, "bot-message");
    })
    .catch(error => {
        console.error("Error:", error);
        appendMessage("Sorry, something went wrong. Please try again.", "bot-message");
    });
}

function appendMessage(text, className) {
    const chatBox = document.getElementById("chat-box");
    const messageDiv = document.createElement("div");
    messageDiv.className = "message " + className;
    messageDiv.innerHTML = text; // Using innerHTML to allow basic formatting (bold, breaks) from backend
    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Allow Enter key to send
document.getElementById("user-input").addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        sendMessage();
    }
});
