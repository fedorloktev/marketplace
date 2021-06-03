$.get('/marketplace-1.0-SNAPSHOT/get-all-clients', function (data) {
    for (i = 0; i < data.length; i++) {
        var doc = document;
        var tbody = doc.getElementById('table').getElementsByTagName('TBODY')[0];
        var row = doc.createElement("TR");
        tbody.appendChild(row);
        var tdId = doc.createElement("TD");
        var tdName = doc.createElement("TD");
        var tdAddress = doc.createElement("TD");
        var tdLogin = doc.createElement("TD");
        var tdRole = doc.createElement("TD");

        row.appendChild(tdId);
        row.appendChild(tdName);
        row.appendChild(tdAddress);
        row.appendChild(tdLogin);
        row.appendChild(tdRole);

        tdId.innerHTML = data[i].id;
        tdName.innerHTML = data[i].fullName;
        tdAddress.innerHTML = data[i].address;
        tdLogin.innerHTML = data[i].login;
        var roleName;
        if (tdRole.innerHTML = data[i].role.id == 1) {
            var roleName = "Administrator";
        } else {
            var roleName = "User";
        }
        tdRole.innerHTML = roleName;
    }
})