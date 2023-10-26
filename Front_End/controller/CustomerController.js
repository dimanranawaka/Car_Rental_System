let currentUser;
let customer;
let rentId;
let cart = [];
let rent;

let regNum;
let dailyMileage;
let monthlyMileage;
let dailyPrice;
let lostDamage;


$.ajax({
    url:baseUrl+"login",
    method:"get",
    async:false,
    dataType:"json",
    contentType:"application/json",
    success: function (res) {
        currentUser = res.data;
        $("#user").text(res.data.username);
        getCustomer();
    }
})

getCustomer();

function getCustomer() {
    $.ajax({
        url: baseUrl + `rent?username=${currentUser.username}`,
        method:"get",
        async: false,
        dataType: "json",
        success: function (res) {
            customer = res.data;
            console.log(customer);
        }
    });
}

generateNewRentId();

function generateNewRentId() {
    $.ajax({
        url: baseUrl + "rent",
        method:"get",
        async:false,
        dataType:"json",
        contentType: "application/json",
        success: function (res) {
            rentId = res.data;
        }
    });
}

homePageFunction();

function homePageFunction() {
    $("#home").fadeIn();
    $("#home").attr("style","display : block !important");
    $("#manageCar").attr("style","display : none !important");
    $("#manageCart").attr("style","display : none !important");
    $("#manageRent").attr("style","display : none !important");
}

$("#btnHome").on("click", function () {
    homePageFunction();
})