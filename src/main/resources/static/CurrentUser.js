'use strict';

fetch("userApi/auth")
    .then(res => res.json())
    .then(js => {
        $('#headerEmail').append(`<span>${js.email}</span>`);

        // Извлечение имен ролей из массива объектов ролей
        const roleNames = js.roles.map(role => role.role);

        // Объединение имен ролей в строку
        const roleString = roleNames.join(', ');

        // Вывод ролей в заголовок страницы
        $('#headerRole').append(`<span>${roleString}</span>`);

        const user = `<tr>
            <td>${js.id}</td>
            <td>${js.username}</td>
            <td>${js.lastname}</td>
            <td>${js.age}</td>
            <td>${js.email}</td>
            <td>${roleString}</td>
        </tr>`;
        $('#userTable').append(user);
    });

/* let roles = "";
            js.role.forEach(role => {roles += role.role + " ";});*/

/*
fetch("userApi/auth") .then(res => res.json()) .then(js => { $('#headerEmail').append(`<span>${js.email}</span>`)
    const roleNames = js.roles.map(role => role.name);
    const roleString = roleNames.join(', ')
    $('#headerRole').append(`<span>${roleString}</span>`)
    const user =
        `<tr>
<td>${js.id}</td>
<td>${js.username}</td>
<td>${js.lastname}</td>
<td>${js.age}</td>
<td>${js.email}</td>
<td>${roleString}</td>
</tr>`;
    $('#userTable').append(user) })*/
