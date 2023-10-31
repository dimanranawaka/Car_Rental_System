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