// Declare global variables

let currentUser;
let customer;
let rentId;
let cart = [];
let rent;

let regNum;
let dailyMileage;
let monthlyMileage;
let dailyPrice;
let monthlyPrice;
let lostDamage;

// Perform a synchronous AJAX request to fetch user information and customer details

$.ajax({
    url:baseUrl+"login",
    method:"get",
    async:false,
    dataType:"json",
    contentType:"application/json",
    success: function (res) {
        currentUser = res.data; // Assign the user data to currentUser
        $("#user").text(res.data.username);
        getCustomer(); // Call the getCustomer function to fetch customer details
    }
})

// Call the getCustomer function to fetch customer details

getCustomer();

// Function to fetch customer details

function getCustomer() {
    $.ajax({
        url: baseUrl + `rent?username=${currentUser.username}`,
        method:"get",
        async: false,
        dataType: "json",
        success: function (res) {
            customer = res.data; // Assign the customer data to the customer variable
            console.log(customer);
        }
    });
}

// Function to generate a new rent ID
generateNewRentId();

// Function to generate a new rent ID
function generateNewRentId() {
    $.ajax({
        url: baseUrl + "rent",
        method:"get",
        async:false,
        dataType:"json",
        contentType: "application/json",
        success: function (res) {
            rentId = res.data; // Assign the new rent ID to the rentId variable
        }
    });
}

// Invoking Page functions

homePageFunction();

console.log("homePageFunction() : executed!");

carPageFunction();

console.log("carPageFunction() : executed!");

performCartFunctions();

console.log("performCartFunctions() : executed!");

performRentFunctions();

console.log("performRentFunctions() : executed!");

performPaymentFunctions();

console.log("performPaymentFunctions() : executed!");

// Function to set up the home page

function homePageFunction() {

    // Show the home page and hide other sections

    $("#home").fadeIn();
    $("#home").attr("style","display : block !important");
    $("#manageCar").attr("style","display : none !important");
    $("#manageCart").attr("style","display : none !important");
    $("#manageRent").attr("style","display : none !important");
}

// Event handler for clicking the "Home" button
$("#btnHome").on("click", function () {
    homePageFunction();
});

// Function to set up the car page
function carPageFunction() {
    // Show the car page and hide other sections
    $("#btnCar").on("click", function () {

        $("#home").attr("style", "display : none !important");
        $("#manageCar").attr("style", "display : block !important");
        $("#manageCart").attr("style", "display : none !important");
        $("#manageRent").attr("style", "display : none !important");

        // Fetch car data using AJAX
        $.ajax({
            url: baseUrl + "car",
            method: "get",
            success: function (res) {
                loadAllCars(res.data); // Call the loadAllCars function to display car data
            }
        });

        // Function to load all car data on the page
        function loadAllCars(cars) {

            $("#cars").empty();
            for (let car of cars) {

                // Append car information to the page

                $("#cars").append(`<div class="col col-lg-3">
            <div class="card">
                <img src="../assets/images/${car.photos.front}" class="card-img-top" height="230px" alt="car">

                <div class="card-body">
                    <h5 class="card-title">${car.brand}</h5>

                    <section class="mb-4">
                        <img src="../assets/images/${car.photos.back}" class="w-25" alt="${car.photos.back}">
                        <img src="../assets/images/${car.photos.side}" class="w-25" alt="car">
                        <img src="../assets/images/${car.photos.interior}" class="w-25" alt="car">
                    </section>

                    <section class="d-flex gap-3 justify-content-between">
                        <p class="card-text"><i class="bi bi-fuel-pump-diesel-fill me-1 text-success"></i>${car.fuelType}</p>
                        <p class="card-text"><i class="bi bi-palette-fill me-1 text-danger"></i>${car.color}</p>
                        <p class="card-text"><i class="bi bi-gear-wide-connected me-1 text-info"></i>${car.transmissionType}</p>
                        <p class="card-text"><i class="bi bi-people-fill me-1 text-primary"></i>${car.passengers}</p>
                    </section>

                    <section class="row justify-content-between p-0 m-0 g-0">
                        <p class="card-text col col-md-5">Free Mileage</p>
                        <p class="card-text text-secondary col col-lg-3 mb-lg-0 mb-4">${car.freeMileage.dailyRate}km Daily</p>
                        <p class="card-text text-secondary col col-lg-3 mb-lg-0 mb-4 text-end">${car.freeMileage.monthlyRate}km Monthly</p>
                    </section>

                    <section class="row justify-content-between p-0 m-0 g-0">
                        <p class="card-text col col-4">Price</p>
                        <p class="card-text text-secondary col col-lg-4 mb-lg-0 mb-4">${car.price.dailyPriceRate} LKR Daily</p>
                        <p class="card-text text-secondary col col-lg-4 mb-lg-0 mb-4 text-end">${car.price.monthlyPriceRate} LKR Monthly</p>
                    </section>

                    <section class="row justify-content-between">
                        <p class="card-text col col-lg-4">Lost Damage Cost</p>
                        <p class="card-text text-secondary col text-end">${car.lostDamageCost} LKR</p>
                    </section>

                    <section class="row justify-content-between">
                        <p class="card-text text-secondary col col-6" id="registerNum"><i class="bi bi-car-front me-1"></i>${car.regNum}</p>
                        <p class="card-text text-secondary col col-6 text-danger">${car.availability == "YES" ? "" : "Out Of Stock"}</p>
                    </section>

                    <section class="d-flex justify-content-between flex-lg-row flex-column gap-1">
                        <button class="btn btn-success rent" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><p class="card-text"><i class="bi bi-upc-scan"></i> Rent </p></button>
                        <button class="btn btn-warning cart" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><p class="card-text"><i class="bi bi-cart-check-fill"></i> Add to cart</p></button>
                    </section>

                </div>

            </div>
            
            
        </div>`);


            }

            getDetail();
            bindButtonEvents();

        }
        // Event handler for the search input
        $("#search").on("keyup", function () {
            // Fetch filtered car data using AJAX based on search criteria
            let text = $("#search").val();
            let searchBy = $("#searchBy").val();
            let fuel = $("#fuelTypes").val();

            $.ajax({
                url: baseUrl + `car/filterByRegNum?text=${text}&search=${searchBy}&fuel=${fuel}`,
                method: "get",
                dataType: "json",
                contentType: "application/json",
                success: function (res) {
                    loadAllCars(res.data); // Call the loadAllCars function to display filtered car data
                }
            });
        });

        // Event handler for changing search criteria
        $("#searchBy , #fuelTypes").change(function () {
            // Fetch filtered car data using AJAX based on updated search criteria
            let text = $("#search").val();
            let searchBy = $("#searchBy").val();
            let fuel = $("#fuelTypes").val();

            $.ajax({
                url: baseUrl + `car/filterByRegNum?text=${text}&search=${searchBy}&fuel=${fuel}`,
                method: "get",
                dataType: "json",
                contentType: "application/json",
                success: function (res) {
                    loadAllCars(res.data); // Call the loadAllCars function to display filtered car data
                }
            });

        });
        // Function to get car details when a "Rent" or "Add to Cart" button is clicked
        function getDetail() {

            $(".rent, .cart").on("click", function () {

                regNum = $(this).parent().parent().children(":eq(6)").children(":eq(0)").text();
                dailyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(1)").text();
                monthlyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
                dailyPrice = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
                monthlyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
                lostDamage = $(this).parent().parent().children(":eq(5)").children(":eq(1)").text();

            });

        }
        // Function to bind button events
        function bindButtonEvents() {

            $(".rent").on("click", function () {
                $("#btnRequestCar").text("Request");
                $("#lostDamageCost").val(lostDamage);
            });

            $(".cart").on("click", function () {
                $("#btnRequestCar").text("Add to Cart");
                $("#lostDamageCost").val(lostDamage);
                setCosts();
            });
        }
        // Event handler for changing pick-up or return date
        $("#pickUpDate").on("click",function () {
            setCosts();
        });
        
        $("#returnDate").on("click",function () {
            setCosts();
        });
        // Function to calculate and set costs based on selected dates
        function setCosts() {

            let days = (new Date(Date.parse($("#returnDate").val()) - Date.parse($("#pickUpDate").val()))) / 1000 / 60 / 60 / 24;
            let carCost = days < 30 ? dailyPrice.split(" ")[0] * days : monthlyPrice.split(" ")[0] * (days / 30);
            $("#carCost").val(carCost);
            $("#driverCost").val(1000 * days);
            $("#cost").val(parseFloat($("#carCost").val()) + parseFloat($("#driverCost").val()))

        }
        
    });
}
// Function to perform cart-related functions
function performCartFunctions() {
    // Event handler for the "Cart" button
    $("#btnCart").on("click",function () {
        // Show the cart page and hide other sections
        $("#home").attr("style","display : none !important");
        $("#manageCar").attr("style","display : none !important");
        $("#manageCart").attr("style","display : block !important");
        $("#manageRent").attr("style","display : none !important");
        $("#payments").attr("style","display : none !important");

        if ( cart.length != 0){
            $("#rent-context").empty();
        }
        // Display cart content
        $("#rent-context").append(`
            
            <div class="card text-center p-2 w-75 shadow">
                    <p class="card-text">Status : ${rent.status}</p>
                    <p class="card-text">Total Cost : ${rent.cost}</p>
                    <p class="card-text">Description : ${rent.description}</p>
                    <p class="card-text">Pick Up Time: ${rent.pickUpDate.toString().replaceAll(",", "-")}</p>
                    <p class="card-text">Pick Up Time: ${rent.pickUpTime.toString().replaceAll(",", ":")}</p>
                    <p class="card-text">Return Date : ${rent.returnDate.toString().replaceAll(",", "/")}</p>
                    <p class="card-text">Return Time : ${rent.returnTime.toString().replaceAll(",", ":")}</p>
                    <p class="card-text">Description : ${rent.description.split(".")[0]}</p>
                                  
                    <table class="table" id=${rent.rentId}>
                        <thead>
                              <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Register Number</th>
                                    <th scope="col">Car Cost</th>
                                    <th scope="col">Driver</th>
                                    <th scope="col">Driver Cost</th>
                              </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                        </tbody>
                    </table>
                
                <section class="mb-2">
                    <button class="btn btn-success btnMultiPurchase"><i class="bi bi-upc-scan"></i> Purchase</button>
                    <button class="btn btn-danger"><i class="bi bi-calendar-x-fill"></i> Cancel</button>
                </section>  
                 
                </div> 
        
        `);
        // Loop through items in the cart and display their details
        for (let rentDetail of rent.rentDetails) {
            let photo;

            $.ajax({
                url: baseUrl + "car?regNum=" + rentDetail.regNum,
                async: false,
                method : "get",
                dataType: "json",
                success: function (res) {
                    photo = res.data.photos.front;
                    console.log(res);
                }
            });
            // Display cart item details
            $(`#${rent.rentId}`).append(`
                <tr>
                
                    <td><img src="../assets/images/${photo}" width="150px" height="80px" alt=""></td>
                    <td>${rentDetail.regNum}</td>
                    <td>${rentDetail.carCost}</td>
                    <td>${rent.driverRequset}</td>
                    <td>${rentDetail.driverCost == null ? 0.00 : rentDetail.driverCost}</td>
                    
                </tr>
                
            `);
        }
        // Event handler for the "Purchase" button in the cart
        $(".btnMultiPurchase").on("click" , function () {

            rent.rentDetails = cart; // // Update rent details with cart contents

            console.log(rent);

            // Send a request to create the rental transaction
            $.ajax({

                url: baseUrl + "rent",
                method: "post",
                data: JSON.stringify(rent),
                dataType:"json",
                contentType:"application/json",
                success: function (res) {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'SuccessFully Requested!',
                        showConfirmButton:false,
                        timer:1600
                    });


                }

            });



        });

        
    });

}

// Function to perform rent-related functions
function performRentFunctions() {
    // Event handler for the "Rent" button
    $("#btnRent").on("click", function () {
        // Show the rent page and hide other sections
        $("#home").attr("style", "display : none !important");
        $("#manageCar").attr("style", "display : none !important");
        $("#manageCart").attr("style", "display : none !important");
        $("#manageRent").attr("style", "display : block !important");
        $("#payments").attr("style", "display : none !important");
        // Fetch rent data using AJAX
        $.ajax({
            url: baseUrl + `rent?nic=` + customer.nic,
            async:false,
            method: "get",
            dataType: "json",
            success: function (res) {

                $("#manage-rent-context").empty();

                for (let rent of res.data) {
                    // Display rent details
                    $("#manage-rent-context").append(`
                <div class="card text-center pt-5 p-2 shadow-sm col col-6">
                    <h5 class="card-text">Rent ID : ${rent.rentId}</h5>
                    <p class="card-text">Status : ${rent.status}</p>
                    <p class="card-text">Total Cost : ${rent.cost}</p>
                    <p class="card-text">Description : ${rent.description}</p>
                    <p class="card-text">Pick Up Time: ${rent.pickUpDate.toString().replaceAll(",", "-")}</p>
                    <p class="card-text">Pick Up Time: ${rent.pickUpTime.toString().replaceAll(",", ":")}</p>
                    <p class="card-text">Return Date : ${rent.returnDate.toString().replaceAll(",", "/")}</p>
                    <p class="card-text">Return Time : ${rent.returnTime.toString().replaceAll(",", ":")}</p>
                    <p class="card-text">Description : ${rent.description.split(".")[0]}</p>
                                  
                    <table class="table" id="rent${rent.rentId}">
                        <thead>
                              <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Register Number</th>
                                    <th scope="col">Car Cost</th>
                                    <th scope="col">Driver</th>
                                    <th scope="col">Driver Cost</th>
                              </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                        </tbody>
                    </table>
                </div>
                `);

                    for (let rendDetail of rent.rentDetails) {

                        let photo;

                        $.ajax({
                            url: baseUrl + "car?regNum=" + rendDetail.regNum,
                            async: false,
                            method: "get",
                            dataType: "json",
                            success: function (res) {
                                photo = res.data.photos.front;
                            }
                        });
                        // Display rent item details
                        $(`#rent${rent.rentId} > tbody`).append(`
                            <tr>
                                <td><img src="../assets/${photo}" width="150px" height="80px" alt=""></td>
                                <td>${rendDetail.regNum}</td>
                                <td>${rendDetail.carCost}</td>
                                <td>${rent.driverRequest}</td>
                                <td>${rendDetail.driverCost == null ? 0.00 : rendDetail.driverCost}</td>
                            </tr>
                        `);

                    }

                }

            }
        });
    });

}
// Function to perform payment-related functions
function performPaymentFunctions() {

    $("#btnManagePayment").on("click", function () {

        $("#home").attr("style","display : none !important");
        $("#manageCar").attr("style","display : none !important");
        $("#manageCart").attr("style","display : none !important");
        $("#manageRent").attr("style","display : none !important");
        $("#manageRent").attr("style","display : block !important");

        $.ajax({

            url: baseUrl + `payment?nic=` + customer.nic,
            method: "get",
            dataType:"json",
            success: function (res) {

                $("#tblPayment").empty();

                for (let datum of res.data) {

                    $("#tblPayment").append(`
                
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.rentId.rentId}</td>
                        <td>${payment.type}</td>
                        <td>${payment.description}</td>
                        <td>${payment.total}</td>
                        <td>${payment.cash}</td>
                        <td>${payment.balance}</td>
                        <td>${payment.date.toString().replaceAll(",", "-")}</td>
                        <td>${payment.time.toString().replaceAll(",", ":")}</td>
                    </tr>
                
                `);

                }

            }

        });

    });


}
// Event handler for requesting a car or adding to the cart
$("#btnRequestCar").on("click", function () {
    // Create a JSON object with rent details and car information

    let json = {
        rentId: rentId,
        nic: customer,
        pickUpDate: $("#pickUpDate").val(),
        pickUpTime: $("#pickUpTime").val(),
        returnDate: $("#returnDate").val(),
        returnTime: $("#returnTime").val(),
        driverRequest: $('#driverRequest').is(':checked') ? "YES" : "NO",
        status: "Pending",
        cost: $("#cost").val(),
        description: $("#description").val(),
        rentDetails: [{
            rentId: rentId,
            nic: null,
            regNum: regNum,
            driverCost: $("#driverCost").val(),
            carCost: $("#carCost").val()
        }]

    }

// Create a JSON object with rent details and car information
    if ($(this).text() == "Request") {
        // Send a request to create the rental transaction
        $.ajax({
            url: baseUrl + "rent",
            method: "post",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {

                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Successfully Requested..!',
                    showConfirmButton: false,
                    timer: 1500
                });

            }
        });
    // Update the cart with the car information
    } else {
        rent = json;
        cart.push({
            rentId: rentId,
            nic: null,
            regNum: regNum,
            driverCost: $("#driverCost").val(),
            carCost: $("#carCost").val()
        });
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Successfully Added..!',
            showConfirmButton: false,
            timer: 1500
        });
    }
    // Update the lost cost
    $("#lostCost").text(lostDamage.toString().replaceAll(" LKR", ""));

});

// Event handler for making a payment for lost damage
$("#make-payment").on("click", function () {
    // Create a JSON object to represent the payment
    let json = {
        balance: 0,
        cash: lostDamage.toString().replaceAll(" LKR", ""),
        description: "Lost Damage Cost Paid",
        total: lostDamage.toString().replaceAll(" LKR", ""),
        type: "Lost_Damage_Payment",
        rentId: {
            rentId: rentId
        }
    }
// Make an AJAX request to submit the payment
    $.ajax({
        url: baseUrl + `payment`,
        async: false,
        method: "post",
        data: JSON.stringify(json),
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            saveAlert();
        },
        error: function () {
            errorAlert("Something Went Wrong..!")
        }
    });
})

// Event handlers to populate customer information
$("#cusNic").val(customer.nic);
$("#cusName").val(customer.name);
$("#cusLicense").val(customer.license);
$("#cusAddress").val(customer.address);
$("#cusContact").val(customer.contact);
$("#cusEmail").val(customer.email);
$("#cusUsername").val(customer.user.username);
$("#cusPassword").val(customer.user.password);
$("#cusNicImgContext").attr(`style`, `background : url(..${customer.nicImage}); background-position: center; background-size: cover`);
$("#cusLicenseImgContext").attr(`style`, `background : url(..${customer.licenseImage}); background-position: center; background-size: cover`);

// Event handler for updating customer information
$("#btnUpdateCustomer").on("click", function () {
    // Create a JSON object to represent the customer information update
    let json = {
        nic: $("#cusNic").val(),
        name: $("#cusName").val(),
        license: $("#cusLicense").val(),
        address: $("#cusAddress").val(),
        contact: $("#cusContact").val(),
        email: $("#cusEmail").val(),
        user: {
            username: $("#cusUsername").val(),
            password: $("#cusPassword").val(),
        }

    }

    $.ajax({
        url: baseUrl + "customer",
        method: "put",
        async: false,
        data: JSON.stringify(json),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            updateAlert();
        }
    });
});

$.ajax({
    url: baseUrl + "rent?nic=" + customer.nic,
    async: false,
    method: "get",
    dataType: "json",
    success: function (res) {

        for (let rent of res.data) {

            if (rent.status != "Closed") {
                $("#cusNic").prop("disabled", true);
                $("#cusName").prop("disabled", true);
                $("#cusLicense").prop("disabled", true);
                $("#cusAddress").prop("disabled", true);
                $("#cusEmail").prop("disabled", true);
                $("#cusUsername").prop("disabled", true);
                $("#cusPassword").prop("disabled", true);
                break;
            }

        }

    }
});

