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

// Invoking Page functions

homePageFunction();

console.log("homePageFunction() : executed!");

carPageFunction();

console.log("carPageFunction() : executed!");

performCartFunctions();

console.log("performCartFunctions() : executed!");

performRentFunctions();

console.log("performRentFunctions() : executed!");

function homePageFunction() {
    $("#home").fadeIn();
    $("#home").attr("style","display : block !important");
    $("#manageCar").attr("style","display : none !important");
    $("#manageCart").attr("style","display : none !important");
    $("#manageRent").attr("style","display : none !important");
}

$("#btnHome").on("click", function () {
    homePageFunction();
});

function carPageFunction() {
    $("#btnCar").on("click", function () {

        $("#home").attr("style", "display : none !important");
        $("#manageCar").attr("style", "display : block !important");
        $("#manageCart").attr("style", "display : none !important");
        $("#manageRent").attr("style", "display : none !important");

        $.ajax({
            url: baseUrl + "car",
            method: "get",
            success: function (res) {
                loadAllCars(res.data);
            }
        });


        function loadAllCars(cars) {

            $("#cars").empty();
            for (let car of cars) {
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

        $("#search").on("keyup", function () {

            let text = $("#search").val();
            let searchBy = $("#searchBy").val();
            let fuel = $("#fuelTypes").val();

            $.ajax({
                url: baseUrl + `car/filterByRegNum?text=${text}&search=${searchBy}&fuel=${fuel}`,
                method: "get",
                dataType: "json",
                contentType: "application/json",
                success: function (res) {
                    loadAllCars(res.data);
                }
            });
        });

        $("#searchBy , #fuelTypes").change(function () {

            let text = $("#search").val();
            let searchBy = $("#searchBy").val();
            let fuel = $("#fuelTypes").val();

            $.ajax({
                url: baseUrl + `car/filterByRegNum?text=${text}&search=${searchBy}&fuel=${fuel}`,
                method: "get",
                dataType: "json",
                contentType: "application/json",
                success: function (res) {
                    loadAllCars(res.data);
                }
            });

        });

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
        
        $("#pickUpDate").on("click",function () {
            setCosts();
        });
        
        $("#returnDate").on("click",function () {
            setCosts();
        });
        
        function setCosts() {

            let days = (new Date(Date.parse($("#returnDate").val()) - Date.parse($("#pickUpDate").val()))) / 1000 / 60 / 60 / 24;
            let carCost = days < 30 ? dailyPrice.split(" ")[0] * days : monthlyPrice.split(" ")[0] * (days / 30);
            $("#carCost").val(carCost);
            $("#driverCost").val(1000 * days);
            $("#cost").val(parseFloat($("#carCost").val()) + parseFloat($("#driverCost").val()))

        }
        
    });
}

function performCartFunctions() {

    $("#btnCart").on("click",function () {

        $("#home").attr("style","display : none !important");
        $("#manageCar").attr("style","display : none !important");
        $("#manageCart").attr("style","display : block !important");
        $("#manageRent").attr("style","display : none !important");
        $("#payments").attr("style","display : none !important");

        if ( cart.length != 0){
            $("#rent-context").empty();
        }

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

        $(".btnMultiPurchase").on("click" , function () {

            rent.rentDetails = cart;

            console.log(rent);

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

function performRentFunctions() {

    $("#btnManagePayment").on("click", function () {

        $("#home").attr("style", "display : none !important");
        $("#manageCar").attr("style", "display : none !important");
        $("#manageCart").attr("style", "display : none !important");
        $("#manageRent").attr("style", "display : none !important");
        $("#payments").attr("style", "display : block !important");

        $.ajax({
            url: baseurl + `payment?nic=` + customer.nic,
            method: "get",
            dataType: "json",
            success: function (res) {

                $("#tblPayment").empty();

                for (let payment of res.data) {
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

