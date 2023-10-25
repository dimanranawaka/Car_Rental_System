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

