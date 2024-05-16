'use strict';

fetch("userApi/auth")
    .then(res => res.json())
    .then(js => {
            $('#headerUsername').append(`<span>${js.username}</span>`);
            $('#headerID').append(`<span>${js.id}</span>`);
        // Извлечение имен ролей из массива объектов ролей
        const roleNames = js.roles.map(role => role.role);

        // Объединение имен ролей в строку
        const roleString = roleNames.join(', ');

        // Вывод ролей в заголовок страницы
        $('#headerRole').append(`<span>${roleString}</span>`);

        const user = `<tr>
            <td>${js.id}</td>
            <td>${js.username}</td>
            <td>${roleString}</td>
        </tr>`;
        $('#userTable').append(user);
    });