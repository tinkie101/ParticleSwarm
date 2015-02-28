import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by tinkie101 on 2015/02/28.
 */
public class FileHandler {
    public static void writeFile(String fileName, String content) throws IOException {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(content);
            out.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
