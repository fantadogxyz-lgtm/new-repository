

// создаём STOMP‑клиент
const client = new StompJs.Client({
    webSocketFactory: () => new SockJS("/ws-chat"),
    reconnectDelay: 5000
});

document.addEventListener("DOMContentLoaded", () => {
    const username = localStorage.getItem("username") || null;
    document.getElementById("user-info").textContent = `Вы вошли как ${username}!`;
});


// подписка на топик
client.onConnect = () => {
    client.subscribe("/topic/messages", (message) => {
        const msg = JSON.parse(message.body);
        const chatBox = document.getElementById("chat-box");

        const time = new Date(msg.sendTime).toLocaleString();


        const p = document.createElement("p");

        const timeSpan = document.createElement("span");
        timeSpan.className = "time";
        timeSpan.textContent = `[${time}] `;

        const userSpan = document.createElement("span");
        userSpan.className = "username";
        userSpan.textContent = `${msg.sender}: `;

        const messageSpan = document.createElement("span");
        messageSpan.textContent = msg.messageContent;

        p.appendChild(timeSpan);
        p.appendChild(userSpan);
        p.appendChild(messageSpan);
        chatBox.prepend(p);
    });
};

function hello(){
    alert("hello");
}

// отправка сообщения
function sendMessage() {
    if (!client.connected) { console.error("STOMP not connected yet"); alert("нету соединения с sw("); return; }

    const input = document.getElementById("message-input");
    const messageContent = input.value;
    if(messageContent.trim() === "") {alert("Вы не можете отправить пустое сообщение."); return;}
    const sender = localStorage.getItem("username");
    client.publish({ destination: "/app/send",
                    body: JSON.stringify({ sender, messageContent }) });
    input.value = "";

}

// запускаем соединение
client.activate();

function checkAuth(){
    const username = localStorage.getItem("username") || null;
    const auth = localStorage.getItem("auth") || false;

    if(username === null || auth === false) {
        alert("Извините, но вам нужно авторизоватся, чтобы получить доступ к сообщениям");
        goIndex();
    }
}


// загрузка истории при открытии
async function loadMessages() {
    checkAuth();
    try {
        const res = await fetch("/messages", { method: "GET" });
        if (res.ok) {
            let messages = await res.json();

            // сортировка: новые выше
            messages.sort((a, b) => new Date(b.sendTime) - new Date(a.sendTime));

            const chatBox = document.getElementById("chat-box");
            chatBox.innerHTML = "";

            messages.forEach(msg => {


                const time = new Date(msg.sendTime).toLocaleString();

                const p = document.createElement("p");

                const timeSpan = document.createElement("span");
                timeSpan.className = "time";
                timeSpan.textContent = `[${time}] `;

                const userSpan = document.createElement("span");
                userSpan.className = "username";
                userSpan.textContent = `${msg.sender}: `;

                const messageSpan = document.createElement("span");
                messageSpan.textContent = msg.messageContent;

                p.appendChild(timeSpan);
                p.appendChild(userSpan);
                p.appendChild(messageSpan);
                chatBox.appendChild(p);

            });
        }
    } catch (err) {
        console.error("Ошибка загрузки сообщений:", err);
    }
}

function goIndex(){
    window.location.href = "/";
}
const message_input = document.getElementById("message-input");
message_input.addEventListener('keydown', event => {
    if (event.key === "Enter" || event.keyCode === 13 ) {
        sendMessage();
    }
});
document.addEventListener("DOMContentLoaded", loadMessages);
