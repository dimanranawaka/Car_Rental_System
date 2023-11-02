package lk.ijse.spring.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImagePathWriterUtil {
    public static String projectPath = "D:\\Dev\\Java\\My_Projects\\Car_Rental_System\\Front_End\\assets";

    public String writeImage(MultipartFile file, Path location) throws IOException, URISyntaxException {

        Files.write(location, file.getBytes());
        file.transferTo(location);

        return location.toString().replace(projectPath, "");

    }
}
