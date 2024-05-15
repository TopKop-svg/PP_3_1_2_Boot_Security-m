/*async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;
    form.role.value = user.role;
}*/
async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;

    const roles = user.roles.map(role => role.role).join(', ');
    console.log("Roles:", roles); // Выводим роли в консоль

    form.role.value = roles;
}