$.validator.addMethod("regex", function (value, element, regexp) {
    var re = new RegExp(regexp);
    return this.optional(element) || re.test(value);
},
    "Please check your input."
);

$.validator.addMethod("dateTime", function (value, element) {
    var stamp = value.split(" ");
    var validDate = !/Invalid|NaN/.test(new Date(stamp[0]).toString());
    var validTime = /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(stamp[1]);
    return this.optional(element) || (validDate && validTime);
}, "Please enter a valid date and time.");

$(function () {
    var now = new Date();
    var timeTmp = now.toLocaleString('ru-RU', { timeZone: 'Europe/Saratov' });
    console.log(timeTmp);
    var time = timeTmp.replaceAll('.', '-');
    time = time.replaceAll(',', '');
    var currYear = time.substr(6, 4);
    var currMounth = time.substr(3, 2);
    var currDay = time.substr(0, 2);
    var currTime = time.substr(11, 8);
    var currDateTime = currYear + "-" + currMounth + "-" + currDay + " " + currTime;

    var queryUrl;

    var getData = location.search;
    var f = getData.split("=");
    console.log(f.length);
    if (f.length <= 2) {
        var seller = decodeURI(f[1]).split('&')[0];
        console.log(seller);
        queryUrl = '/marketplace-1.0-SNAPSHOT/add-lot';
    } else if (f.length > 2) {
        queryUrl = '/marketplace-1.0-SNAPSHOT/update-lot';
        var prodId = decodeURI(f[1]).split('&')[0];
        var prodName = decodeURI(f[2]).split('&')[0];
        var prodDesc = decodeURI(f[3]).split('&')[0];
        var stPrice = decodeURI(f[4]).split('&')[0];
        var stopDate = decodeURI(f[5]).split('&')[0];
        var seller = decodeURI(f[6]).split('&')[0];
        console.log(getData);
        console.log(prodId);
        console.log(prodName);
        console.log(prodDesc);
        console.log(stPrice);
        console.log(stopDate);
        console.log(seller);

        document.getElementById("productName").disabled = true;
        document.getElementById("startPrice").disabled = true;
        document.getElementById("productDescription").disabled = true;
        document.getElementById("minBid").disabled = true;
        document.getElementById("productName").value = prodName;
        document.getElementById("productDescription").value = prodDesc;
        document.getElementById("startPrice").value = stPrice;
        document.getElementById("stopDate").value = stopDate;
    }

    $("form[name='addNewLot']").validate({
        rules: {
            prodName: {
                required: true,
                minlength: 1,
                maxlength: 25,
                regex: "^[a-zA-Z]+(?:[ .]+[a-zA-Z]+)*$",
            },
            description: {
                required: true,
                maxlength: 100,
            },
            startPrice: {
                required: true,
                minlength: 1,
                maxlength: 25,
                regex: "^[0-9.]+$",
            },
            minBid: {
                required: true,
                minlength: 1,
                maxlength: 25,
                regex: "^[0-9.]+$",
            },
            stopDate: {
                required: true,
                minlength: 1,
                maxlength: 25,
                dateTime: true,
                min: currDateTime,
            },
        },
        messages: {
            prodName: "<br><label class = \"validMess\">Enter product name.</label>",
            description: "<br><label class = \"validMess\">Enter product description.</label>",
            startPrice: "<br><label class = \"validMess\">Enter start price.</label>",
            minBid: "<br><label class = \"validMess\">Enter min bid.</label>",
            stopDate: "<br><label class = \"validMess\">Invalid stop date.</label>",
        },
        submitHandler: function (form) {
            $.ajax({
                url: queryUrl,
                dataType: 'json',
                type: 'POST',
                cache: false,
                contentType: 'application/json',
                data: JSON.stringify({
                    id: prodId,
                    productId: {
                        userId: {
                            fullName: seller,
                        },
                        name: $("#productName").val(),
                        description: $("#productDescription").val(),
                    },
                    startPrice: $("#startPrice").val(),
                    bidMin: $("#minBid").val(),
                    stopDate: $("#stopDate").val(),
                })
            })

            console.log(prodId);
            console.log($("#productName").val());
            console.log($("#productDescription").val());
            console.log($("#startPrice").val());
            console.log($("#stopDate").val());
        }
    });
});