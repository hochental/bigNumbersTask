import java.io.*;
import java.util.*;


public class FirstExampleImplementation implements SumatorInterface{

    @Override
    public ArrayList<String> readFile(String filePath) throws IOException {
        File file = new File(filePath);
        ArrayList<String> outputList=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                outputList.add(line);
            }
        }
        return outputList;
    }

    @Override
    public void run(String file) {
        ArrayList<String> resultList=new ArrayList<>();
        try {
            resultList=readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String s: resultList){
            String []splittedArray = s.split(";");
            System.out.println(math(splittedArray[0], splittedArray[1], splittedArray[2]));
        }
    }

    @Override
    public boolean math(String a, String b, String result){
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        int lengthA=a.length();
        int lengthB=b.length();

        int operator = A.length>B.length?A.length:B.length;
        char[] C = new char[operator];
        char zero = '0';

        for (int single = 0; operator > 0; single /= 10) {
            if (lengthA > 0)
                single += A[--lengthA] - zero;
            if (lengthB > 0)
                single += B[--lengthB] - zero;
            C[--operator] = (char) (zero + single % 10);
        }
        return new String(C, operator, C.length - operator).equals(result);
    }


}