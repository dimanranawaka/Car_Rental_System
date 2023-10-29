package lk.ijse.spring.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImagePathWriterUtil {
    public static String projectPath = "";

    public String imagePathWriter(@org.jetbrains.annotations.NotNull MultipartFile multipartFile, Path pathLocation) throws IOException {
        Files.write(pathLocation, multipartFile.getBytes());
        multipartFile.transferTo(pathLocation);

        return pathLocation.toString().replace("","");
    }
}
