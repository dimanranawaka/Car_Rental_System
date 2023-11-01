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
// When the "btnHome" button is clicked, call the homeLoader() and dataLoader() functions
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

        // Request Available Car Amount

        $.ajax({
           url: baseUrl+"car/count",
           method:"get",
           dataType: "json",
           success: function (res) {

               $("#available-cars").text(res.data);

           }
        });

        // Request Reserved Car Amount

        $.ajax({
            url: baseUrl+"car/count/reserved",
            method:"get",
            dataType: "json",
            success: function (res) {

                $("#reserved-cars").text(res.data);

            }
        });

        // Request In-Maintain Car Amount

        $.ajax({
            url: baseUrl+"car/count/maintain",
            method:"get",
            dataType: "json",
            success: function (res) {

                $("#maintain-cars").text(res.data);

            }
        });

        // Request Available Drivers Amount

        $.ajax({
            url: baseUrl+"driver/available",
            method:"get",
            dataType: "json",
            success: function (res) {

                $("#available-drivers").text(res.data);

            }
        });

        // Request Reserved Drivers

        $.ajax({
            url: baseUrl+"driver/reserved",
            method:"get",
            dataType: "json",
            success: function (res) {

                $("#reserved-drivers").text(res.data);

            }
        });

    }

// Configure options for the daily income chart
    var dataPoints = [];


    var options = {

        animationEnabled : true,
        theme : "light2",
        title : {
            text : "Daily Income"
        },
        axisX : {
            valueFormatString : "DD MM YYYY",
        },
        axisY : {
            title: "LKR",
            titleFontSize :24
        },
        data :[{
            type : "spline",
            yValueFormatString : "$#, ###.##",
            dataPoints : dataPoints
        }]

    };
// Request daily income data and update the chart
    $.ajax({
        url : baseUrl + "payment/daily",
        method: "get",
        success : function (res) {

            for (let i = 0; i < res.data.length.length; i++) {
                    dataPoints.push({
                        x: new Data(res.data[i][0]),
                        y: res.data[i][0]
                    });
            }
            $("#chartContainer").CanvasJSchart(options);
        }
    });

    let points = [];
// Configure options for the car brands chart
    var brandOptions = {
        title : {
            text: "Car Brands"
        },
        subtitles : [{
            text : "About Car Brands"
        }],
        animationEnabled: true,
        data : [{
            type: "pie",
            startAngle :40,
            toolTipContent : "<b>{label}</b>>: {y}%",
            showInLegend : "true",
            legendText : "{label}",
            indexLabelFontSize :16,
            indexLabel : "{label} - {y}%",
            dataPoints : points
        }]
    };
// Request car brand data and update the chart
    $.ajax({
        url: baseUrl+"car/brand",
        method:"get",
        success: function (res) {

            for (let i = 0; i < res.data.length.length; i++) {

                points.push({
                    y: res.data[i][1],
                    label:res.data[i][0]
                });

            }
            $("#brandChart").CanvasJSChart(brandOptions);
        }
    });

}


function performCustomerFunctions() {

    $("#btnCustomer").on("click",function () {

        $('#viewCustomer').fadeIn();
        $("#viewCustomer").attr("style","display:block !important");
        $("#manageCustomers").attr("style","display:none !important");
        $("#manageCar").attr("style","display:none !important");
        $("#viewCar").attr("style","display:none !important");
        $("#manageDriver").attr("style","display:none !important");
        $("#drivers").attr("style","display:none !important");
        $("#rents").attr("style","display:none !important");
        $("#reports").attr("style","display:none !important");

        // Load uploaded NIC image

        loadSelectedImage("#cusNicImage");

        // Load uploaded License image

        loadSelectedImage("#cusLicenseImage");

        $("#btnAddNewCustomer").on("click", function () {

            $("#btnSaveCustomer").text("Save");

        });

        $("#btnCustomer").on("click", function () {

            let data = new FormData($("#customerForm")[0]);

            let json = {

                nic: $("#cusNic").val(),
                name:$("#cusName").val(),
                license:$("#cusLicense").val(),
                address:$("#cusAddress").val(),
                contact:$("#cusContact").val(),
                email:$("#cusEmail").val(),
                user:{

                    username:$("#cusUsername").val(),
                    password:$("#cusPassword").val(),

                }
            }

            s.ajax({

                url: baseUrl+ "customer",
                method: $("#btnSaveCustomer").text() == "Save" ? "post" : "put",
                async: false,
                data: JSON.stringify(json),
                contentType: "application/json",
                dataType:"json",
                success: function (res) {

                    $("#btnSaveCustomer").text() == "Save" ? saveAlert() : updateAlert();


                }

            });

            if ($("#btnSaveCustomer").text() == "Save"){

                s.ajax({

                    url: baseUrl+ "customer?image",
                    method: "post",
                    async: false,
                    data:data,
                    contentType: false,
                    processData: false,
                    success: function (res) {

                        saveAlert();


                    }

                });

            }

        });

    });
}