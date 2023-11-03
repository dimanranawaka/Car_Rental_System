let currentUser;

$.ajax({
    url: baseUrl + "driver",
    method: "get",
    async: false,
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        currentUser = res.data;
        $("#user").text(res.data.user.username);
    }
});

$.ajax({
    url: baseUrl + "driver?nic=" + currentUser.nic,
    method: "get",
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        for (let detail of res.data) {

            let rent;

            $.ajax({
                url: baseUrl + "rent?rentId=" + detail.rentId,
                async:false,
                method: "get",
                dataType: "json",
                contentType: "application/json",
                success:function (res) {
                    rent = res.data;
                }
            })

            $("#tblDriverSchedule").append(`
                <tr>
                    <td>${detail.rentId}</td>
                    <td>${rent.pickUpDate.toString().replaceAll(",","-")}</td>
                    <td>${rent.pickUpTime.toString().replaceAll(",",":")}</td>
                    <td>${rent.returnDate.toString().replaceAll(",","-")}</td>
                    <td>${rent.returnTime.toString().replaceAll(",",":")}</td>
                    <td>${detail.regNum}</td>
                </tr>
            `);
        }
    }
});