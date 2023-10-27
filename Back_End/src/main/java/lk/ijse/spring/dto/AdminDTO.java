package lk.ijse.spring.dto;

import lk.ijse.spring.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AdminDTO {
    private String nic;
    private String name;
    private String license;
    private String address;
    private String contact;
    private String email;
    private User user;
}
