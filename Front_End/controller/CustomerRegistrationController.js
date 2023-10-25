/** Save Customer method */

$("#btnSaveCustomer").on("click",function () {

    let data = new FormData($("#customerForm")[0]);

    let json  = {
        nic: $("#cusNic").val(),
        name :$("#cusName").val(),
        license :$("#cusLicense").val(),
        address :$("#cusAddress").val(),
        contact :$("#cusContact").val(),
        email :$("#cusEmail").val(),
        user:{
            username:$("#cusUsername").val(),
            password:$("#cusPassword").val(),
        }
    }

    $.ajax({
        url: baseUrl + "customer",
        async: false,
        cache: false,
        data: JSON.stringify(json),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            saveAlert();
        }
    });

    if ($('#cusNicImage').get(0).files.length === 0 || $('#cusLicenseImage').get(0).files.length === 0){

        return;

    }

    $.ajax({
        url: baseUrl + "customer?image",
        method: "post",
        async: false,
        data: data,
        contentType: false,
        processData: false,
        success: function (res) {

            window.open("login_form.html",'_self');

        },

        error:function (res) {
            alert(res.message);
        }

    });

});

// RegEx for customer_register page

const cusNameRegEx = /^[A-z ]{5,20}$/;
const cusEmailRegEx = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-]+)(\.[a-zA-Z]{2,5}){1,2}$/;
const cusNicRegEx = /^[0-9]{9,10}[A-z]?$/;
const cusAddressRegEx = /^[0-9/A-z. ,]{5,}$/;
const cusPasswordRegEx = /^[0-9/A-z. ,]{5,}$/;
const cusContactRegEx = /^[0-9]{10}$/;


// Let's put those Reg-Ex to a array

let customerValidations = [];

customerValidations.push({
    reg: cusNameRegEx,
    field : $('#cusName'),
    error : 'Customer Name Pattern is Invalid ! : A-z 5-20'
});


customerValidations.push({
    reg: cusEmailRegEx,
    field: $('#cusEmail'),
    error: 'Customer E-mail Pattern is Invalid ! : dimanranawaka@gmail.com'
});

customerValidations.push({
    reg:cusNicRegEx,
    field:$('#cusNic'),
    error: 'Customer NIC Pattern is Invalid ! : 970790448'
});

customerValidations.push({
    reg:cusAddressRegEx,
    field:$('#cusAddress'),
    error:'Customer Address Pattern is Invalid ! : A-z 0-9 ,/'
});

customerValidations.push({
    reg:cusContactRegEx,
    field:$('#cusLicense'),
    error: 'Customer License Pattern is Invalid ! : 12345678'
});

customerValidations.push({
    reg:cusContactRegEx,
    field:$('#cusContact'),
    error: 'Customer Contact Pattern is Invalid ! : 077123456'
});

customerValidations.push({
    reg:cusPasswordRegEx,
    field:$('#cusPassword'),
    error: 'Customer Password Pattern is Invalid ! : 077123456'
});

customerValidations.push({
    reg:cusPasswordRegEx,
    field:$('#cusRe-password'),
    error: 'Customer Re-Password Pattern is Invalid ! : 077123456'
});

$("#cusName, #cusNic, #cusLicense, #cusContact, #cusAddress , #cusEmail, #cusUsername, #cusPassword, #cusRe-password").on('keyup', function (event) {
    checkValidity(customerValidations);
});

$("#cusName, #cusNic, #cusLicense, #cusContact, #cusAddress , #cusEmail, #cusUsername, #cusPassword, #cusRe-password").on('blur', function (event) {
    checkValidity(customerValidations);
});
