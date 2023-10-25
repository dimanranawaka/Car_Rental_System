/** Save Customer method */

$("#btnSaveCustomer").on("click",function () {

    let data = new FormData($("#customerForm")[0]);

    let json : = {
        nic: $("#cusNic").val(),
        name :$("#cusName").val(),
        license :$("#cusLicense").val(),
        address :$("#cusAddress").val(),
        contact :$("#cusContact").val(),
        email :$("#cusEmail").val(),
        user:{
            username:$("#cusUsername"),
            password:$("#cusPassword"),
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
        url: baseUrl + "customer ? image",
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
const cusContactRegEx = /^[0-9]{10}$/;
const cusSalaryRegEx = /^[0-9]{1,}[.]?[0-9]{1,2}$/;

