$("#viewCar").fadeIn();

$.ajax({
    url: baseUrl+ "car",
    method: "get",

    success:function (res) {
        loadAllCars(res.data);
    }
});

function loadAllCars(cars) {

}