package lk.ijse.spring.controller;

import lk.ijse.spring.service.UserService;
import lk.ijse.spring.util.ResponseUtil;
import lk.ijse.spring.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class UserController {
    @Autowired
    UserService service;

    @PostMapping
    public ResponseUtil getUser(@RequestParam String username, @RequestParam String password){
        return new ResponseUtil("Ok","Successfully Logged-In!",service.getUser(username,password));
    }

    @GetMapping
    public ResponseUtil getCurrentUserDetails(){
        return new ResponseUtil("Ok","User Details Loaded Successfully!", UserUtil.currentUser);
    }
}
