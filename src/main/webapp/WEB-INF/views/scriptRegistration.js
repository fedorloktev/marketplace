$.validator.addMethod(
    "regex",
    function (value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);

$(function () {
    $("form[name='registration']").validate({
        rules: {
            fullname: {
                required: true,
                minlength: 1,
                maxlength: 25,
                regex: "^[a-zA-Z]+(?:[ .]+[a-zA-Z]+)*$",
            },
            address: {
                maxlength: 100,
            },
            login: {
                required: true,
                minlength: 1,
                maxlength: 25,
                regex: "^[a-zA-Z_\-]+$",
            },
            password: {
                required: true,
                minlength: 1,
                maxlength: 25,
            },
            repassword: {
                required: true,
                minlength: 1,
                maxlength: 25,
                equalTo: password,
            },
        },
        messages: {
            fullname: "<br><label class = \"validMess\">Enter your full name.</label>",
            login: "<br><label class = \"validMess\">Enter your login.</label>",
            password: "<br><label class = \"validMess\">Enter your password.</label>",
            repassword: "<br><label class = \"validMess\">Repeate your password.</label>",
        },
        submitHandler: function (form) {

            var defer = $.ajax({
                url: '/marketplace-1.0-SNAPSHOT/add-new-user',
                dataType: 'json',
                type: 'POST',
                cache: false,
                contentType: 'application/json',
                data: JSON.stringify({
                    fullName: $("#fullname").val(),
                    address: $("#address").val(),
                    login: $("#login").val(),
                    password: $("#password").val(),
                })
            });
            defer.done(function (data) {
                console.log(data);
                if (data == false) {
                    alert("This login is busy. Change your login!");
                }
                else {
                    document.location.href = 'auction.html?fullname=' + $("#fullname").val();
                }
            })

        }
    });
});