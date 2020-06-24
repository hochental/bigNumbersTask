import java.io.*;
import java.util.*;


public class FirstExampleImplementation implements SumatorInterface{

    @Override
    public HashMap<Integer, String> readFile(String filePath) throws IOException {
        long startTime = System.nanoTime();
        File file = new File(filePath);
        HashMap<Integer, String> outputList=new HashMap<>();
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                outputList.put(i,line);
                i++;
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("time for file loading (same for both) "+totalTime+ " nanoseconds");
        return outputList;
    }

    @Override
    public void run(String file) {
        long startTime = System.nanoTime();
        HashMap<Integer, String> resultList=new HashMap<>();
        try {
            resultList=readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter=0;
        for(String s: resultList.values()){
            String []splittedArray = s.split(";");
            if(math(splittedArray[0], splittedArray[1], splittedArray[2])){
                counter++;
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("GammaSoft: Przetwarzanie wierszy trwalo "+(double)totalTime/1_000_000_000.0+"s"+" Przetworzono wierszy: "+resultList.size()+", w tym sum prawidłowych:  "+counter);
    }

    @Override
    public boolean math(String a, String b, String result){
        if(a.equals("0")){
            return b.equals(result);
        }else if(b.equals("0")){
            return a.equals(result);
        }
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int lengthA=A.length;
        int lengthB=B.length;

        int operator = lengthA>lengthB?lengthA:lengthB;
        operator++;
        char[] C = new char[operator];
        char zero = '0';

        for (int single = 0; operator > 0; single /= 10) {
            if (lengthA > 0)
                single += A[--lengthA] - zero;
            if (lengthB > 0)
                single += B[--lengthB] - zero;
                C[--operator] = (char) (zero + single % 10);
        }
        String s;
        if(C[0]=='0'){
            System.arraycopy(C, 1, C, 0, C.length-1);
            s=new String(C, operator, C.length-1 );
        }else{
            s=new String(C, operator, C.length-operator );
        }
        return s.equals(result);
    }


}
