async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.lastName.value = user.lastname;
    form.email.value = user.email;
    form.age.value = user.age;

    const roles = user.roles.map(role => role.role).join(', ');
    console.log("Roles:", roles);

    form.role.value = roles;
}