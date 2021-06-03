showLots();
var userFullname;
var keyName = "";

$(function () {
    if (location != null && location.search != null) {
        var get = location.search;
        var f = get.split("=");
        console.log(f[1]);
        if (f[1] == null) {
            document.getElementById("searchMyProd").disabled = true;
            document.getElementById("addLot").disabled = true;
        } else {
            var fullname = decodeURI(f[1]);
            userFullname = decodeURI(f[1]);
            document.getElementById('username').innerHTML = fullname;
        }
    }
})

function showLots(clicked_id) {
    var now = new Date();
    var timeTmp = now.toLocaleString();
    var time = timeTmp.replaceAll('.', '-');
    time = time.replaceAll(',', '');
    var currYear = time.substr(6, 4);
    var currMounth = time.substr(3, 2);
    var currDay = time.substr(0, 2);
    var currTime = time.substr(11, 8);
    var currDateTime = currYear + "-" + currMounth + "-" + currDay + " " + currTime;
    console.log(currDateTime);

    var word = "";
    if (clicked_id == "findAll") {
        console.log("It is filter ALL");
        word = "";
        keyName = "";
    } else if (clicked_id == "searchWord") {
        console.log("It search");
        word = document.getElementById("search").value;
    } else if (clicked_id == "searchMyProd") {
        console.log("It's my prod");
        keyName = userFullname;
    }
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange =
        function () {
            if (this.readyState == 4 && this.status == 200) {

                var table = document.getElementById("table");
                while (table.rows.length > 1) {
                    table.deleteRow(1);
                }

                $.get('/marketplace-1.0-SNAPSHOT/get-all-lots', function (data) {
                    for (i = 0; i < data.length; i++) {
                        if (data[i].productId.name.includes(word)) {
                            console.log(keyName);
                            if (data[i].productId.userId.fullName.includes(keyName)) {
                                if ((keyName == "" && data[i].stopDate > currDateTime) || (keyName != "")) {
                                    var doc = document;
                                    var tbody = doc.getElementById('table').getElementsByTagName('TBODY')[0];
                                    var row = doc.createElement("TR");
                                    tbody.appendChild(row);
                                    var tdId = doc.createElement("TD");
                                    var tdName = doc.createElement("TD");
                                    var tdDesc = doc.createElement("TD");
                                    var tdFullname = doc.createElement("TD");
                                    var tdStartPrice = doc.createElement("TD");
                                    var tdBidMin = doc.createElement("TD");
                                    var tdBestOffer = doc.createElement("TD");
                                    var tdBidder = doc.createElement("TD");
                                    var tdStopDate = doc.createElement("TD");
                                    var tdBid = doc.createElement("TD");

                                    row.appendChild(tdId);
                                    row.appendChild(tdName);
                                    row.appendChild(tdDesc);
                                    row.appendChild(tdFullname);
                                    row.appendChild(tdStartPrice);
                                    row.appendChild(tdBidMin);
                                    row.appendChild(tdBestOffer);
                                    row.appendChild(tdBidder);
                                    row.appendChild(tdStopDate);
                                    row.appendChild(tdBid);

                                    tdId.innerHTML = data[i].id;
                                    tdName.innerHTML = data[i].productId.name;
                                    tdDesc.innerHTML = data[i].productId.description;
                                    tdFullname.innerHTML = data[i].productId.userId.fullName;
                                    tdStartPrice.innerHTML = data[i].startPrice;
                                    tdBidMin.innerHTML = data[i].bidMin;
                                    tdBestOffer.innerHTML = data[i].bestPrice;
                                    var bidName;
                                    if (data[i].bidderId == null) {
                                        bidName = "";
                                    } else {
                                        bidName = data[i].bidderId.fullName;
                                    }
                                    tdBidder.innerHTML = bidName;
                                    tdStopDate.innerHTML = data[i].stopDate;
                                    if (userFullname == data[i].productId.userId.fullName) {
                                        tdBid.innerHTML = "<label>Your lot</label>";
                                    } else if (userFullname == null) {
                                        tdBid.innerHTML = "<button type=\"button\" onClick=\"document.location='login.html'\">Login</button>";
                                    } else if (userFullname != null) {
                                        tdBid.innerHTML = "<input id=\"bid\" class=\"inputBid\" placeholder=\"10.5\" pattern=\"^[0-9.]+$\">" +
                                            "<button id=\"doBid\" name =\"doBid\"type=\"button\" class=\"buttonBidding\" onClick = \"makeBid($(this).closest('tr').index())\">Bid</button>";
                                    }


                                    tdId.onclick = function () {
                                        changeLot($(this).closest('tr').index());
                                    };
                                }
                            }
                        }
                    }
                    $("#addLot").click(function () {
                        document.location.href = 'addLot.html?seller=' + userFullname;
                    })
                })
            }
        };
    xhttp.open("GET", "/marketplace-1.0-SNAPSHOT/get-all-lots");
    xhttp.send();
}

function makeBid(idBid) {
    var currBestPrice;
    var currNewPrice;
    var currMinBid;
    var currStartPrice;
    var lotId;

    var indexRow = null;

    console.log("This is line: " + idBid);
    indexRow = idBid;
    currBestPrice = $("#table" + " tbody tr").eq(indexRow).find('td').eq(6).text();
    currNewPrice = $("#table" + " tbody tr").eq(indexRow).find('td').eq(9).find('input').val();
    currMinBid = $("#table" + " tbody tr").eq(indexRow).find('td').eq(5).text();
    currStartPrice = $("#table" + " tbody tr").eq(indexRow).find('td').eq(4).text();
    lotId = $("#table" + " tbody tr").eq(indexRow).find('td').eq(0).text();
    bidder = userFullname;

    console.log("id lot: " + lotId);
    console.log("Press index = " + indexRow);
    console.log("Best price = " + currBestPrice);
    console.log("New price = " + currNewPrice);
    console.log("Min bid = " + currMinBid);

    if ((currBestPrice != "" && (currNewPrice - currBestPrice) >= currMinBid) ||
        (currBestPrice == "" && (currNewPrice - currStartPrice) >= currMinBid)) {
        var defer = $.ajax({
            url: '/marketplace-1.0-SNAPSHOT/update-bid',
            dataType: 'json',
            type: 'POST',
            cache: false,
            contentType: 'application/json',
            data: JSON.stringify({
                id: lotId,
                bestPrice: currNewPrice,
                bidderId: {
                    fullName: bidder,
                },
            })
        });
        defer.done(function (data) {
            showLots();
            alert("Your bid is accepted")
        })
    } else {
        alert("Your bid less than minimum bid");
    }
    indexRow = null;

}

function changeLot(rowId) {
    console.log(rowId);
    var prodId;
    var prodName;
    var prodDesc;
    var startPrice;
    var stopDate;
    var seller;

    prodId = $("#table" + " tbody tr").eq(rowId).find('td').eq(0).text();
    prodName = $("#table" + " tbody tr").eq(rowId).find('td').eq(1).text();
    prodDesc = $("#table" + " tbody tr").eq(rowId).find('td').eq(2).text();
    startPrice = $("#table" + " tbody tr").eq(rowId).find('td').eq(4).text();
    stopDate = $("#table" + " tbody tr").eq(rowId).find('td').eq(8).text();
    seller = $("#table" + " tbody tr").eq(rowId).find('td').eq(3).text();

    console.log("id = " + prodId);
    console.log("name = " + prodName);
    console.log("desc = " + prodDesc);
    console.log("stPr = " + startPrice);
    console.log("stDate = " + stopDate);
    console.log("seller = " + seller);

    if (userFullname == seller) {
        document.location.href = 'addLot.html?prodId=' + prodId + "&prodName=" + prodName +
            "&prodDesc=" + prodDesc + "&startPrice=" + startPrice + "&stopDate=" + stopDate + "&seller=" + seller;
    } else {
        alert("You can't edit this lot!")
    }

}