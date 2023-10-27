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
carPageFunction();

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

        $("#home").attr("style","display : none !important");
        $("#manageCar").attr("style","display : block !important");
        $("#manageCart").attr("style","display : none !important");
        $("#manageRent").attr("style","display : none !important");

        $.ajax({
            url: baseUrl + "car",
            method: "get",
            success: function (res) {
                loadAllCars(res.data);
            }
        });

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



    }

    $("#search").on("keyup" ,function () {

        let text = $("#search").val();
        let searchBy = $("#searchBy").val();
        let fuel = $("#fuelTypes").val();

        $.ajax({
            url: baseUrl + `car/filterByRegNum?text=${text}&search=${searchBy}&fuel=${fuel}`,
            method:"get",
            dataType:"json",
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
            method:"get",
            dataType:"json",
            contentType: "application/json",
            success: function (res) {
                loadAllCars(res.data);
            }
        });

    });

    function getDetail() {

        $(".rent, .cart").on("click" , function () {

            regNum =$(this).parent().parent().children(":eq(6)").children(":eq(0)").text();
            dailyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(1)").text();
            monthlyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
            dailyPrice = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
            monthlyMileage = $(this).parent().parent().children(":eq(4)").children(":eq(2)").text();
            lostDamage = $(this).parent().parent().children(":eq(5)").children(":eq(1)").text();

        });

    }

}