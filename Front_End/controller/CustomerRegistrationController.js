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
    })

})