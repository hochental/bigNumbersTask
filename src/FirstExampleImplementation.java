import java.io.*;
import java.util.*;


public class FirstExampleImplementation implements SumatorInterface{

    @Override
    public ArrayList<String> readFile(String filePath) throws IOException {
        long startTime = System.nanoTime();
        File file = new File(filePath);
        ArrayList<String> outputList=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                outputList.add(line);
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
        ArrayList<String> resultList=new ArrayList<>();
        try {
            resultList=readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter=0;
        for(String s: resultList){
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

    public StringBuilder fillWithZeros(String toFill, int size){
        StringBuilder s=new StringBuilder();
        for(int i=0; i<size-toFill.length(); i++){
            s.append("0");
        }
        return new StringBuilder(s+toFill);
    }

    @Override
    public boolean math(String a, String b, String result){
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        int negativeOperatorA=A[0]=='-'?1:0;
        int negativeOperatorB=B[0]=='-'?1:0;

        if((negativeOperatorA==1 && negativeOperatorB==0)|| (negativeOperatorA==0 && negativeOperatorB==1)){

            if(negativeOperatorA==1)
                a=a.substring(1);
            if(negativeOperatorB==1)
                b=b.substring(1);
            if(result.toCharArray()[0]=='-')
                result=result.substring(1);

            if(a.equals(b) && result.equals("0")){
                return true;
            }

            int size = a.length()>b.length()?a.length():b.length();
            String temp;

            if(b.length()>=a.length() && b.toCharArray()[0]>=a.toCharArray()[0]){
                temp=a;
                a=b;
                b=temp;
            }

            if(a.length()>b.length()){
                b=fillWithZeros(b, size).toString();
            }else if(a.length()<b.length()){
                a=fillWithZeros(a, size).toString();
            }

            StringBuilder sb= new StringBuilder();
            char []A1=a.toCharArray();
            char []B1=b.toCharArray();

            for(int i=size-1; i>=0; i--){
                if(A1[i]<B1[i]){
                    A1[i-1]--;
                    sb.append(Character.getNumericValue(A1[i])+10-Character.getNumericValue(B1[i]));
                }else{
                    sb.append(Character.getNumericValue(A1[i])-Character.getNumericValue(B1[i]));
                }
            }
            return sb.reverse().toString().equals(result);
        }

        int lengthA=a.length();
        int lengthB=b.length();

        int operator = A.length>B.length?A.length:B.length;
        char[] C = new char[operator];
        char zero = '0';

        for (int single = 0; operator > 0; single /= 10) {
            if (lengthA > 1 && negativeOperatorA==1)
                single += A[--lengthA] - zero;
            if (lengthA > 0 && negativeOperatorA==0)
                single += A[--lengthA] - zero;
            if (lengthB > 1 && negativeOperatorB==1)
                single += B[--lengthB] - zero;
            if (lengthB > 0 && negativeOperatorB==0)
                single += B[--lengthB] - zero;
            C[--operator] = (char) (zero + single % 10);
        }

        String s = new String(C, operator, C.length+  operator);
        return s.toCharArray()[0]=='0'?s.substring(1).equals(result.substring(1)):s.equals(result);
    }


}
