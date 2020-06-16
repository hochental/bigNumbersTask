import java.io.IOException;
import java.util.ArrayList;

public interface SumatorInterface {
    void run(String file);
    ArrayList<String> readFile(String filePath) throws IOException;
    boolean math(String a, String b, String c);
}
