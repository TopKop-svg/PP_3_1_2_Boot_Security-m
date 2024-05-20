tableOfAllUsers();
const table = $('#usersTable');
function tableOfAllUsers() {
    fetch("http://localhost:8088/adminApi/users")
        .then(res => res.json())
        .then(data => {
            table.empty();
            data.forEach(user => {
                let roles = "";
                user.roles.forEach(role => {
                    roles += role.role + " ";
                });
                let usersTable = `<tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>${roles}</td>
                <td>
                    <button type="button" class="btn btn-sm btn-info"
                        data-bs-toggle="modal"
                        data-bs-target="#editModal"
                        onclick="editModalData(${user.id})">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-sm btn-danger" 
                        data-toggle="modal"
                        data-bs-target="#deleteModal"
                        onclick="deleteModalData(${user.id})">Delete</button>
                </td>
            </tr>`;
                table.append(usersTable);
            });
        })
        .catch(error => {
            console.error("Error fetching user data:", error);
        });
}
