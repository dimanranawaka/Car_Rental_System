package lk.ijse.spring.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImagePathWriterUtil {
    public static String projectPath = "D:\\Dev\\Java\\My_Projects\\Car_Rental_System\\Front_End\\assets";

    public String imagePathWriter(MultipartFile multipartFile, Path pathLocation) throws IOException, URISyntaxException {

        Files.write(pathLocation,multipartFile.getBytes());

        multipartFile.transferTo(pathLocation);

        return pathLocation.toString().replace("D:\\Dev\\Java\\My_Projects\\Car_Rental_System\\Front_End\\assets","");
    }
}
