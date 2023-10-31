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

// Function to load the home page and initialize data loading
function homeLoader() {
    // Show the "home" section and hide other sections
    $("#home").fadeIn();
    $("#home").attr("style", "display: block !important");
    $("#viewCustomer").attr("style", "display: none !important");
    // ... (similar lines for other sections)
    $("#reports").attr("style", "display: none !important");
}

// Function to perform actions when the home button is clicked
function performHomePageFunctions() {
    // When the "btnHome" button is clicked, call the homeLoader() and dataLoader() functions
    $("#btnHome").on("click", function () {
        homeLoader(); // Load the home section
        dataLoader(); // Load data
    });

    dataLoader(); // Load data initially

    // Function to load various data using AJAX requests
    function dataLoader() {
        // Request the number of registered customers and update the "reg-users" element
        $.ajax({
            url: baseUrl + "customer/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#reg-users").text(res.data);
            }
        });
        // Request the number of rents and update the "rent-count" element
        $.ajax({
            url: baseUrl + "rent/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#rent-count").text(res.data);
            }
        });
        // Request the number of available cars and update the "available-cars" element
        $.ajax({
            url: baseUrl + "car/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#available-cars").text(res.data);
            }
        });
        // ... (similar lines for other data requests)
    }

    // Configure options for the daily income chart
    var dataPoints = [];
    var options = {
        animationEnabled: true,
        theme: "light2",
        title: {
            text: "Daily Income"
        },
        axisX: {
            valueFormatString: "DD MM YYYY",
        },
        axisY: {
            title: "LKR",
            titleFontSize: 24
        },
        data: [{
            type: "spline",
            yValueFormatString: "$#, ###.##",
            dataPoints: dataPoints
        }]
    };

    // Request daily income data and update the chart
    $.ajax({
        url: baseUrl + "payment/daily",
        method: "get",
        success: function (res) {
            for (let i = 0; i < res.data.length.length; i++) {
                dataPoints.push({
                    x: new Data(res.data[i][0]),
                    y: res.data[i][0]
                });
            }
            $("#chartContainer").CanvasJSchart(options);
        }
    });

    // Configure options for the car brands chart
    let points = [];
    var brandOptions = {
        title: {
            text: "Car Brands"
        },
        subtitles: [{
            text: "About Car Brands"
        }],
        animationEnabled: true,
        data: [{
            type: "pie",
            startAngle: 40,
            toolTipContent: "<b>{label}</b>>: {y}%",
            showInLegend: "true",
            legendText: "{label}",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}%",
            dataPoints: points
        }]
    };

    // Request car brand data and update the chart
    $.ajax({
        url: baseUrl + "car/brand",
        method: "get",
        success: function (res) {
            for (let i = 0; i < res.data.length.length; i++) {
                points.push({
                    y: res.data[i][1],
                    label: res.data[i][0]
                });
            }
            $("#brandChart").CanvasJSChart(brandOptions);
        }
    });
}
