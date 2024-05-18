let formEdit = document.forms["formEditUser"];
const id_edit = document.getElementById("id_edit");
const username_edit = document.getElementById("username_edit");
const password_edit = document.getElementById("password_edit");
const closeEditButton = document.getElementById("editFormCloseButton");
const editUserButton = document.getElementById("editUserButton");

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
        // Отображение модального окна
        modal.show();
        // Загрузка данных пользователя
        const response = await fetch(`http://localhost:8088/adminApi/user/${id}`);
        /*if (!response.ok) {
            throw new Error("Failed to fetch user data");
        }*/
        const user = await response.json();

        // Заполнение полей ID и Username
        id_edit.value = user.id;
        username_edit.value = user.username;

        // Загрузка всех ролей
        const allRoles = await loadAllRoles();

        // Заполнение списка выбора ролей
        const roleSelect = document.getElementById("roleEdit");
        roleSelect.innerHTML = ""; // Очищаем список перед заполнением

        // Добавляем опции для каждой роли
        allRoles.forEach(role => {
            const option = document.createElement("option");
            option.value = role.id;
            option.text = role.role;
            roleSelect.appendChild(option);
        });

        // Устанавливаем выбранные роли для текущего пользователя
        user.roles.forEach(userRole => {
            for (let option of roleSelect.options) {
                if (option.value === userRole.id) {
                    option.selected = true;
                }
            }
        });
        editUserButton.addEventListener("click", submitHandler);


        // Удаляем предыдущие обработчики события submit, чтобы избежать дублирования
        //formEdit.removeEventListener("submit", submitHandler);

        // Добавляем новый обработчик события submit
        //formEdit.addEventListener("submit", submitHandler);

    } catch (error) {
        console.error(error.message);
    }
}

// Обработчик отправки формы
async function submitHandler(event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию

    const roleSelect = document.getElementById("roleEdit");
    const selectedRoles = Array.from(roleSelect.selectedOptions).map(option => ({
        id: option.value,
        role: option.text
    }));

    const editedUser = {
        id: id_edit.value,
        username: username_edit.value,
        password: password_edit.value,
        roles: selectedRoles
    };

    try {
        const response = await fetch(`http://localhost:8088/adminApi/user/${id_edit.value}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editedUser)
        });

       /* if (!response.ok) {
            throw new Error("Failed to update user");
        }*/

        /*// Закрытие модального окна
        const modal = new bootstrap.Modal(document.querySelector('#editModal'));
        modal.hide();*/

        // Обновление данных на странице, если необходимо
        // например, вызов функции, которая обновляет таблицу пользователей
       // editUserButton.click();
       /* tableOfAllUsers();*/

    } catch (error) {
        console.error(error.message);
    }
}


