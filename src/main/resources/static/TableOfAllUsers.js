const table = $('#usersTable');
tableOfAllUsers();
function tableOfAllUsers() {
    table.empty()
    fetch("http://localhost:8088/adminApi/users")
        .then(res => res.json())
        .then(data => {data.forEach(user => {
            let roles = "";
            user.roles.forEach(role => {roles += role.role + " ";});
            let usersTable = `<tr>
          <td>${user.id}</td>
          <td>${user.username}</td>
          <td>${roles}</td>
             <td>
              <button type="button" class="btn btn-sm btn-info"
                data-bs-toogle="modal"
                data-bs-target="#editModal"
                onclick="editModalData(${user.id})">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-sm btn-danger" 
                data-toggle="modal"
                data-bs-target="#deleteModal"
                onclick="deleteModalData(${user.id})">Delete</button>
            </td>
        </tr>)`;
            table.append(usersTable);})
        })
}
