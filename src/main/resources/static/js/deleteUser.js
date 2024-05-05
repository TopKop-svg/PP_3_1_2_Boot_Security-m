'use strict';

const DELETE_FORM_ID = 'formDelete';
const ROLE_ADMIN = '1';
const ROLE_USER = '2';

const deleteForm = document.getElementById(DELETE_FORM_ID);

async function openDeleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await openAndFillInTheModal(deleteForm, modal, id);
}

async function fillInTheModal(deleteForm, modal, id) {
    // Fill in the modal form with the user data
    deleteForm.id.value = id;
    switch (deleteForm.roles.value) {
        case ROLE_ADMIN:
            deleteForm.roles.value = 'ADMIN';
            break;
        case ROLE_USER:
            deleteForm.roles.value = 'USER';
            break;
    }
}

async function deleteUser(id) {
    try {
        const response = await fetch(`adminApi/user/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error(`Error deleting user: ${response.statusText}`);
        }
        $('#closeDelete').click();
        getTableUser();
    } catch (error) {
        console.error(error);
    }
}

deleteForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    await deleteUser(deleteForm.id.value);
});