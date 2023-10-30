package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerImageDTO {
    String nic;
    MultipartFile nicImage;
    MultipartFile licenseImage;
}
