import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public interface SumatorInterface {
    void run(String file);
    HashMap<Integer, String> readFile(String filePath) throws IOException;
    boolean math(String a, String b, String c);
}
