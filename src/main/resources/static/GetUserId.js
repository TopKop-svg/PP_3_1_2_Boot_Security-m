async function getUserId(id) {
    let url = "http://localhost:8088/adminApi/" + id;
    let response = await fetch(url);
    return await response.json();
}