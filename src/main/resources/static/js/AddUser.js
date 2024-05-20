document.addEventListener('DOMContentLoaded', async function () {
    try {
        const response = await fetch('http://localhost:8088/adminApi/roles');
        if (!response.ok) {
            throw new Error('Failed to fetch roles');
        }
        const roles = await response.json();
        const roleSelect = document.getElementById("roleAdd");
        roles.forEach(role => {
            const option = document.createElement("option");
            option.value = role.id;
            option.text = role.role;
            roleSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading roles:', error);
    }
});

let formNew = document.forms["formNewUser"];
let addUserForm = document.getElementById("formNewUser");
let addUserButton = document.querySelector('#addUserButton');

addUserForm.addEventListener("submit", function(event) {
    event.preventDefault();
    addUser();
});

async function addUser() {
    let newUserRoles = Array.from(formNew.roles.selectedOptions).map(option => ({
        id: option.value,
        role: option.text
    }));

    const newUser = {
        username: formNew.username.value,
        lastname: formNew.lastname.value,
        email: formNew.email.value,
        age: formNew.age.value,
        password: formNew.password.value,
        roles: newUserRoles
    };

    console.log("Attempting to add new user:", newUser);

    try {
        const response = await fetch("http://localhost:8088/adminApi/user", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        });

        if (response.ok) {
            console.log("User added successfully");
            formNew.reset();
            tableOfAllUsers();
            $('#home-tab').click();
        } else {
            const errors = await response.json();
            console.error("Failed to add user:", errors);
            // displayAddErrors(errors);
        }
    } catch (error) {
        console.error("Error adding user:", error.message);
    }
}
