let currentUser;

$.ajax({
    url: baseUrl + "driver",
    method:"get",
    async:false,
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        currentUser = res.data;
        $("#user").text(res.data.user.username);
    }
});

$.ajax({
    url: baseUrl + "driver?nic" + currentUser.nic,
    method:"get",
    async:false,
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        for (let datum of res.data) {

            let rent;

            $.ajax({
                url: baseUrl + 'rent?rentId=?' + datum.rentId;
                async: false,
                method:"get",
                dataType: "json",
                contentType:"application/json",
                success: function (res) {
                    rent = rent.data;
                }
            });

            $("#tblDriverSchedule").append(`
                <tr>
                    <td>${datum.rentId}</td>
                    <td>${rent.pickUpDate.toString().replaceAll("","")}</td>
                    <td>${rent.pickUpTime.toString().replaceAll("","")}</td>
                    <td>${rent.x.toString().replaceAll("","")}</td>
                    <td>${rent.y.toString().replaceAll("","")}</td>
                    <td>${datum.regNum}</td>
                </tr>
            `);

        }
    }
})