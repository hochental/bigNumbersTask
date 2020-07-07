import java.io.IOException;
import java.util.*;

public interface SumatorInterface {
    void run(String file);
    List<Integer> readFile(String filePath) throws IOException;
    boolean math(String a, String b, String c);
}
