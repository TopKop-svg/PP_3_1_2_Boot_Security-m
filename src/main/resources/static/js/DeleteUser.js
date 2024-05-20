let formDelete = document.forms["formDeleteUser"];
const id_delete = document.getElementById("id_delete");
const username_delete = document.getElementById("username_delete");
const lastname_delete = document.getElementById("lastname_delete");
const email_delete = document.getElementById("email_delete");
const age_delete = document.getElementById("age_delete");
const closeDeleteButton = document.getElementById("deleteFormCloseButton");
const deleteUserButton = document.getElementById("deleteUserButton");
const deleteModal = document.getElementById("deleteModal");
const bsDeleteModal = new bootstrap.Modal(deleteModal);

async function deleteModalData(id) {
    try {
        const response = await fetch(`http://localhost:8088/adminApi/user/${id}`);
        if (!response.ok) {
            throw new Error("Failed to fetch user data");
        }
        const user = await response.json();

        id_delete.value = user.id;
        username_delete.value = user.username;
        lastname_delete.value = user.lastname;
        email_delete.value = user.email;
        age_delete.value = user.age;

        bsDeleteModal.show();

        const roleSelect = document.getElementById("roleDelete");
        roleSelect.innerHTML = "";

        user.roles.forEach(role => {
            const option = document.createElement("option");
            option.value = role.id;
            option.text = role.role;
            roleSelect.appendChild(option);
        });

        deleteUserButton.addEventListener("click", deleteUser);

    } catch (error) {
        console.error(error.message);
    }
}

async function deleteUser() {
    try {
        const urlDel = `http://localhost:8088/adminApi/user/${id_delete.value}`;
        const method = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        };
        const response = await fetch(urlDel, method);
        if (!response.ok) {
            throw new Error("Failed to delete user");
        }
        closeDeleteButton.click();tableOfAllUsers();
    } catch (error) {
        console.error(error.message);
    }
}

