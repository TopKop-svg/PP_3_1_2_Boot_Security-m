'use strict';
fetch("userApi/auth")
    .then(res => res.json())
    .then(js => {
            $('#headerUsername').append(`<span>${js.email}</span>`);
            $('#headerID').append(`<span>${js.id}</span>`);

        const roleNames = js.roles.map(role => role.role);
        const roleString = roleNames.join(', ');

        $('#headerRole').append(`<span>${roleString}</span>`);

        const user = `<tr>
            <td>${js.id}</td>
            <td>${js.username}</td>
            <td>${js.lastname}</td>
            <td>${js.email}</td>
            <td>${js.age}</td>
            <td>${roleString}</td>
        </tr>`;
        $('#userTable').append(user);
    });