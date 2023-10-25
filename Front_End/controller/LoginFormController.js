import {Alert} from "../assets/js/bootstrap.esm";

$("#btnLogin").on("click",function () {

    $.ajax({
        url:baseUrl+"login",
        method:"post",
        data: $("#loginForm").serialize(),

        success:function (res) {

            switch (res.data.role) {

                case "Admin":
                    window.open();
                    break;
                    
                case "Customer":
                    window.open();
                    break;
                
                case "Driver":
                    window.open();
                    break;
                    
            }
        },
        
        error:function (res) {
            alert("Invalid Username or Password")
        }
    });
})