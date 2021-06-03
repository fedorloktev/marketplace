$.validator.addMethod(
    "regex",
    function (value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);

$(function () {
    $("form[name='signIn']").validate({
        rules: {
            login: {
                required: true,
                minlength: 1,
                regex: "^[0-9a-zA-Z_+&bsol-]+$",
            },
            password: {
                required: true,
                minlength: 1
            },
        },
        messages: { login: "<br><label class = \"validMess\">Enter your login</label>", password: "<br><label class = \"validMess\">Enter your password</label>" },
        submitHandler: function (form) {
            var defer = $.ajax({
                url: '/marketplace-1.0-SNAPSHOT/login-user',
                type: 'POST',
                cache: false,
                contentType: 'application/json',
                data: JSON.stringify({
                    login: $("#login").val(),
                    password: $("#password").val(),
                })
            });
            defer.done(function (fullName) {
                console.log(fullName);
                if (fullName == "") {
                     alert("Incorrect login or password!"); 
                    }
                else {
                    document.location.href = 'auction.html?fullname=' + fullName;
                }
            })
        }
    });
});
