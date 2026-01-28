function render() {
    const app = document.getElementById("mein_menu");

    if (localStorage.getItem("auth") === "true") {
        app.innerHTML = `
      <p>You are logged in as ${localStorage.getItem("username") || "anon"}!</p>
      <button class = "btn-new" onclick="goToChat()">Enter the chat</button>
      <button class = "btn-new" onclick="logout()">Log out</button>
      <br>
    `;
    } else {
        app.innerHTML = `
      <button class = "btn-new" onclick="goToAuth()">Log in or Register</button>
      
      <h2 class="info_auth">You cannot enter the chat until you log in</h2>
    `;
    }
}

function goToAuth() {
    window.location.href = "/auth";
}

function goToChat() {
    const username = localStorage.getItem("username");
    const auth = localStorage.getItem("auth") || false;
    window.location.href = "/chat?username=" + encodeURIComponent(username) + "&auth=" + encodeURIComponent(auth);
}


function logout() {
    localStorage.removeItem("auth");
    localStorage.removeItem("username");
    render();
}

// запускаем рендер сразу после загрузки страницы
document.addEventListener("DOMContentLoaded", render);
