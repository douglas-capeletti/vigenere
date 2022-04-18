import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static String readFromPath(String path) throws IOException {
        Path fileLocation = Paths.get(path);
        return Files.readAllLines(fileLocation).get(0);
    }

    public static void writeOnPath(String path, String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
