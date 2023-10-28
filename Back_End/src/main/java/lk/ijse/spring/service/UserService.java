package lk.ijse.spring.service;

import lk.ijse.spring.dto.UserDTO;

public interface UserService {
    UserDTO getUser(String username, String password);
}
