'use strict';

getCurrentUser()

function getCurrentUser() {
    fetch("userApi/auth")
        .then(res => res.json())
        .then(js => {
            $('#headerEmail').append(`<span>${js.email}</span>`)
            $('#headerUsername').append(`<span>${js.email}</span>`)

         /*   $('#roleCurrentUser').append(`<span>${js.shortRole}</span>`)*/
            const user = `$(
                    <tr>
                        <td>${js.id}</td>
                        <td>${js.username}</td>
                        <td>${js.lastName}</td>
                        <td>${js.age}</td>
                        <td>${js.email}</td>
                      
                    </tr>)`;
            $('#userTable').append(user)

        })
}