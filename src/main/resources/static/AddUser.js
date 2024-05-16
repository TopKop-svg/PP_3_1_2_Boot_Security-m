
let formNew = document.forms["formNewUser"];
let addUserForm = document.getElementById("formNewUser");
let addUserButton = document.querySelector('#addUserButton');

addUserForm.addEventListener("submit", function(event) {
    event.preventDefault();
    addUser();
});

function addUser() {
    let newUserRoles = Array.from(formNew.roles.selectedOptions).map(option => ({
        id: option.value,
        role: "ROLE_" + option.text
    }));

    fetch("http://localhost:8088/adminApi/user", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: formNew.username.value,
            password: formNew.password.value,
            roles: newUserRoles
        })
    }).then(response => {
        if (response.ok) {
            formNew.reset();
            tableOfAllUsers();
            $('#home-tab').click();
        } else {
            response.json().then(errors => {
              /*  displayAddErrors(errors);*/
            });
        }
    });
}

/*function displayAddErrors(errors) {
    let errorAddDiv = document.getElementById("errorAddDiv");
    errorAddDiv.innerHTML = "";
    errors.forEach(error => {
        let errorSpan = document.createElement("span");
        errorSpan.className = "error-message";
        errorSpan.innerHTML = error;
        errorAddDiv.appendChild(errorSpan);
    });
}*/

/*
function loadRolesAdd() {
    let select = document.getElementById("roleAdd");
    select.innerHTML = "";

    fetch("http://localhost:8088/adminApi/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.role === "ROLE_USER" ? "USER" : role.role === "ROLE_ADMIN" ? "ADMIN" : role.name;
                select.appendChild(option);
            });
            addUserForm.querySelector('input[type="submit"]').removeAttribute('disabled');
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesAdd);*/
