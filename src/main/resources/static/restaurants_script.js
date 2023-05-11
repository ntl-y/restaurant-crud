function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("restaurants-table");
    switching = true;
    dir = "asc";
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
        } else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }

    var headers = document.getElementsByClassName("sort-header");
    for (i = 0; i < headers.length; i++) {
        headers[i].getElementsByTagName("span")[0].classList.remove("asc");
        headers[i].getElementsByTagName("span")[0].classList.remove("desc");
    }

    var currentHeader = headers[n];
    var currentIcon = currentHeader.getElementsByTagName("span")[0];
    if (dir == "asc") {
        currentIcon.classList.remove("desc");
        currentIcon.classList.add("asc");
    } else if (dir == "desc") {
        currentIcon.classList.remove("asc");
        currentIcon.classList.add("desc");
    }
}
