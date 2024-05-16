async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.username.value = user.username;

    const roles = user.roles.map(role => role.role).join(', ');
    console.log("Roles:", roles); // Выводим роли в консоль

    form.role.value = roles;
}