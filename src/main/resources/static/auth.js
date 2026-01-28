let regexp = /<(.*?)>/g;


async function login() {
    event.preventDefault(); // чтобы форма не перезагружала страницу
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    //тут вот будет проверочка на валидность ника и пароля
    const fields_errors = [];
    fields_errors.push(validateUsername(username).join("\n"));
    fields_errors.push(validatePassword(password).join("\n"));
    if (fields_errors.length > 0){
        const error_info = document.getElementById("error-info");
        error_info.textContent = fields_errors.join("\n");
        error_info.style.whiteSpace = "pre-line";
        return;
    }

    const error_info = document.getElementById("error-info");
    error_info.textContent = "";
    try {
        const res = await fetch("/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

        if (res.ok) {
            // допустим сервер вернул успех вошли в акк
            localStorage.setItem("auth", "true");
            localStorage.setItem("username", username);
            window.location.href = "/"; // переход на главную
        } else if(res.status === 401) {
            alert("Неверный логин или пароль! Попробуйте войти еще раз или зарегистрируйтесь");
        } else if (res.status === 404){
            alert("Ошибка! Пользователь с таким username не найден! Проверьте username или зарегистрируйтесь");
        } else if (res.status === 500){
            alert("Ошибка. Попрубуйте позже");
        }
    } catch (err) {
        console.error("Ошибка при логине:", err);

    }
}



async function regist(){
    event.preventDefault(); // чтобы форма не перезагружала страницу

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const fields_errors = [];
    fields_errors.push(validateUsername(username).join("\n"));
    fields_errors.push(validatePassword(password).join("\n"));
    if (fields_errors.length > 0){
        const error_info = document.getElementById("error-info");
        error_info.textContent = fields_errors.join("\n");
        error_info.style.whiteSpace = "pre-line";
        return;
    }
    const error_info = document.getElementById("error-info");
    error_info.textContent = "";

    try {
        const res = await fetch("/regist", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });
        if (res.status === 201) {
            // допустим сервер вернул успех
            localStorage.setItem("auth", "true");
            localStorage.setItem("username", username);
            window.location.href = "/"; // переход на главную
        } else if(res.status === 409) {
            alert("Пользователь с таким username уже есть! Для регистрации введите иной username. Если это Вы, то войдите");
        }  else if (res.status === 500){
            alert("Ошибка. Попрубуйте позже");
        }
    } catch (err) {
        console.error("Ошибка при регистрации:", err);
    }
}

function goIndex(){
    window.location.href = "/";
}


function validateUsername(username){
    const errors = [];

    if(!username || username.trim() ===''){
        errors.push("Username can't be empty");
        return errors;
    }

    if (username.length < 4){
        errors.push("Username must have at least 4 characters");
    }

    if (username.length > 50){
        errors.push("Username cannot have more than 50 characters");
    }

    const usernameRegex = /^[A-Za-z][A-Za-z0-9_-]+$/;
    if(!usernameRegex.test(username)){
        if(!/^[A-Za-z]/.test(username)){
            errors.push("Username must start with a letter (A-Z, a-z)");
        }
        if(!/^[A-Za-z][A-Za-z0-9_-]+$/.test(username)){
            errors.push("Username must have only latin letters (A-Z, a-z), numbers and some special symbols(_-)");
        }
    }

    return errors;

}


function validatePassword(password){
    const errors = [];

    if(!password || password.trim() ===''){
        errors.push("Password can't be empty");
        return errors;
    }

    if (password.length < 8){
        errors.push("Password must have at least 8 characters");
    }

    if (password.length > 50){
        errors.push("Password cannot have more than 50 characters");
    }

    const passwordRegex = /^[A-Za-z0-9_-]+$/;
    if(!passwordRegex.test(password)){
            errors.push("Password must have only latin letters (A-Z, a-z), numbers and some special symbols(_-)");
    }

    return errors;


}
