let regexp = /<(.*?)>/g;


async function login() {
    event.preventDefault(); // чтобы форма не перезагружала страницу
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    //тут вот будет проверочка на валидность ника и пароля
    const fields_errors = [];

    const usernameErrors = validateUsername(username).join("\n");
    const passwordErrors = validatePassword(password).join("\n");

    if (usernameErrors) fields_errors.push(usernameErrors);
    if (passwordErrors) fields_errors.push(passwordErrors);

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
            alert("Invalid username or password! Please try again or register");
        } else if (res.status === 404){
            alert("Error! User with this username not found! Check your username or register");
        } else if (res.status === 500){
            alert("Error. Please try again later");
        }
    } catch (err) {
        console.error("Login error:", err);

    }
}



async function regist(){
    event.preventDefault(); // чтобы форма не перезагружала страницу

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const fields_errors = [];

    const usernameErrors = validateUsername(username).join("\n");
    const passwordErrors = validatePassword(password).join("\n");

    if (usernameErrors) fields_errors.push(usernameErrors);
    if (passwordErrors) fields_errors.push(passwordErrors);

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
            alert("User with this username already exists! For registration, please enter a different username. If this is you, please log in");
        }  else if (res.status === 500){
            alert("Error. Please try again later");
        }
    } catch (err) {
        console.error("Registration error:", err);
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
