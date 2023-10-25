import {Alert} from "../assets/js/bootstrap.esm";

$("#btnLogin").on("click",function () {

    $.ajax({
        url:baseUrl+"login",
        method:"post",
        data: $("#loginForm").serialize(),

        success:function (res) {

            switch (res.data.role) {

                case "Admin":
                    window.open("admin_dashboard.html",'_self');
                    break;
                    
                case "Customer":
                    window.open("customer_page.html",'_self');
                    break;
                
                case "Driver":
                    window.open("driver_dashboard.html",'_self');
                    break;
                    
            }
        },
        
        error:function (res) {
            alert("Invalid Username or Password")
        }
    });
})