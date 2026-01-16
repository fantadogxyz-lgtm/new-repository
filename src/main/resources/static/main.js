function render() {
    const app = document.getElementById("mein_menu");

    if (localStorage.getItem("auth") === "true") {
        app.innerHTML = `
      <p>Вы вошли как ${localStorage.getItem("username") || "anon"}!</p>
      <button class = "btn-new" onclick="goToChat()">Пойти в чат</button>
      <button class = "btn-new" onclick="logout()">Выйти</button>
      <br>
    `;
    } else {
        app.innerHTML = `
      <button class = "btn-new" onclick="goToAuth()">Войти или зарегистрироваться</button>
      
      <h2 class="info_auth">Вы не сможете зайти в чат, пока не авторизуетесь</h2>
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
