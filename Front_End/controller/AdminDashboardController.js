let regNum;
let dailyMileage;
let monthlyMileage;
let dailyPrice;
let monthlyPrice;
let rentId;
let currentUser;
let customer;

$.ajax({
    url: baseUrl + "login",
    method: "get",
    async: false,
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        currentUser = res.data;
        $("#user").text(res.data.username);
    }
});

performHomePageFunctions();
homeLoader();

function homeLoader(){
    $("#home").fadeIn();
    $("#home").attr("style","display : block !important");
    $("#viewCustomer").attr("style","display : none !important");
    $("#manageCustomers").attr("style","display : none !important");
    $("#manageCar").attr("style","display : none !important");
    $("#viewCar").attr("style","display : none !important");
    $("#manageDriver").attr("style","display : none !important");
    $("#drivers").attr("style","display : none !important");
    $("#rents").attr("style","display : none !important");
    $("#payments").attr("style","display : none !important");
    $("#reports").attr("style","display : none !important");
}

function performHomePageFunctions(){

    $("#btnHome").on("click", function () {

        homeLoader();
        dataLoader();
    });

    dataLoader();

    function dataLoader() {

        // Request Customers Amount

        $.ajax({

            url: baseUrl+"customer/count",
            method: "get",
            dataType: "json",
            success: function (res) {

                $("#reg-users").text(res.data);

            }

        });

        // Request Rents Amount

        $.ajax({

            url: baseUrl+"rent/count",
            method:"get",
            dataType:"json",
            success: function (res) {

                $("#rent-count").text(res.data);

            }

        });
    }
}