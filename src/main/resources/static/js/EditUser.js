let formEdit = document.forms["formEditUser"];
const id_edit = document.getElementById("id_edit");
const username_edit = document.getElementById("username_edit");
const lastname_edit = document.getElementById("lastname_edit");
const email_edit = document.getElementById("email_edit");
const age_edit = document.getElementById("age_edit");
const password_edit = document.getElementById("password_edit");
const closeEditButton = document.getElementById("editFormCloseButton");
const editUserButton = document.getElementById("editUserButton");
const editModal = document.getElementById("editModal");
const bsEditModal = new bootstrap.Modal(editModal);

async function loadAllRoles() {
    try {
        const response = await fetch('http://localhost:8088/adminApi/roles');
        if (!response.ok) {
            throw new Error('Failed to fetch roles');
        }
        return await response.json();
    } catch (error) {
        console.error(error.message);
    }
}

async function editModalData(id) {
    try {
        const modal = new bootstrap.Modal(document.querySelector('#editModal'));
        modal.show();

        const response = await fetch(`http://localhost:8088/adminApi/user/${id}`);
        const user = await response.json();

        id_edit.value = user.id;
        username_edit.value = user.username;
        lastname_edit.value = user.lastname;
        email_edit.value = user.email;
        age_edit.value = user.age;

        const allRoles = await loadAllRoles();

        const roleSelect = document.getElementById("roleEdit");
        roleSelect.innerHTML = "";

        allRoles.forEach(role => {
            const option = document.createElement("option");
            option.value = role.id;
            option.text = role.role;
            roleSelect.appendChild(option);
        });

        user.roles.forEach(userRole => {
            for (let option of roleSelect.options) {
                if (option.value == userRole.id) {
                    option.selected = true;
                }
            }
        });
        formEdit.addEventListener("submit", submitHandler, modal.hide());
    } catch (error) {
        console.error(error.message);
    }
}

async function submitHandler(event) {
    event.preventDefault();

    const roleSelect = document.getElementById("roleEdit");
    const selectedRoles = Array.from(roleSelect.selectedOptions).map(option => ({
        id: option.value,
        role: option.text
    }));

    const editedUser = {
        id: id_edit.value,
        username: username_edit.value,
        lastname: lastname_edit.value,
        email: email_edit.value,
        age: age_edit.value,
        password: password_edit.value,
        roles: selectedRoles
    };

    const response = await fetch(`http://localhost:8088/adminApi/user/${id_edit.value}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(editedUser)
    });
    closeEditButton.click();
    tableOfAllUsers();

}
