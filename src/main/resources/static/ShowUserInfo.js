showUserInfo();

function showUserInfo() {
    fetch("http://localhost:8088/admin")
        .then(res => res.json())
        .then(user => {
            console.log('userSata', JSON.stringify(user))
            $('#headerUsername').append(user.username);
            let userList = `$(
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>   
                    <td>${user.email}</td>
                    
                </tr>)`;
            $('#userTable').append(userList);
        })
}
