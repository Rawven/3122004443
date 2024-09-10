package rawven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler {
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Path.of(filePath)));
    }

    public void writeFile(String filePath, String content) throws IOException {
        // 以追加模式写入文件
        Files.writeString(Path.of(filePath), "\n"+content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}