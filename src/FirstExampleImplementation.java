import java.io.*;
import java.util.*;


public class FirstExampleImplementation implements SumatorInterface{

    @Override
    public List<Integer> readFile(String filePath) throws IOException {
        File file = new File(filePath);
        int counter=0;
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String []splittedArray = line.split(";");
                if(math(splittedArray[0], splittedArray[1], splittedArray[2])){
                    counter++;
                }
                i++;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(counter);
        list.add(i);
        return list;
    }

    @Override
    public void run(String file) {
        long startTime = System.nanoTime();
        List<Integer> list = null;
        try {
            list = readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        assert list != null;
        System.out.println("GammaSoft: Przetwarzanie wierszy trwalo "+(double)totalTime/1_000_000_000.0+"s"+" Przetworzono wierszy: "+list.get(0)+", w tym sum prawidłowych:  "+list.get(1));
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
