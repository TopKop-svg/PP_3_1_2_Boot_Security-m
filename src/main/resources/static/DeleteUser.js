let formDelete = document.forms["formDeleteUser"];
const id_delete = document.getElementById("id_delete");
const username_delete = document.getElementById("username_delete");
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
        bsDeleteModal.show();
        deleteUserButton.addEventListener("click", deleteUser);
    } catch (error) {
        console.error(error.message);
    }
}

async function deleteUser() {
    try {
        const urlDel = `http://localhost:8088/adminApi/user/2`;
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
        closeDeleteButton.click();
    } catch (error) {
        console.error(error.message);
    }
}

/*deleteUser();*/
/*
async function deleteModalData(id) {
    const  urlForDel = "http://localhost:8088/adminApi/user" + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                username_delete.value = `${user.username}`;

            })

        bsDeleteModal.show();
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}
*/

/*function deleteUser() {
    formDelete .addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8088/adminApi/user" + formDelete .id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteFormCloseButton').click();
            tableOfAllUsers();
        });
    });
}

function loadRolesDelete() {
    let select = document.getElementById("roleDelete");
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
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesDelete);
function loadRolesDelete() {
    let select = document.getElementById("roleDelete");
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
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesDelete);*/
