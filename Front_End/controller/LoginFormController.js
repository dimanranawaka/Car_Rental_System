$("#btnLogin").on("click",function () {
    $.ajax({
        url:baseUrl+"login",
        method:"post",
        data: $("#loginForm").serialize(),
        success:function (res) {
            
        }
    });
})