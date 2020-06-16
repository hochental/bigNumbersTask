import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SecoundExampleImplementation implements SumatorInterface {

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

    public StringBuilder fillWithZeros(String toFill, int size){
        StringBuilder s=new StringBuilder();
        for(int i=0; i<size-toFill.length(); i++){
            s.append("0");
        }
        return new StringBuilder(s+toFill);
    }

    @Override
    public boolean math(String a, String b, String c) {
        int size = a.length()>b.length()?a.length():b.length();
        if(a.length()>b.length()){
            b=fillWithZeros(b, size).toString();
        }else if(a.length()<b.length()){
            a=fillWithZeros(a, size).toString();
        }
        boolean flag=false;
        StringBuilder sb= new StringBuilder();
        int tempResult;

        for(int i=size-1; i>=0; i--){
            tempResult = a.toCharArray()[i]-'0' + b.toCharArray()[i]-'0';
            if(flag) {
                tempResult++;
            }
            flag=false;
            if(tempResult>9){
                flag=true;
                sb.append(tempResult%10);
            }else{
                sb.append(tempResult);
            }
        }
        return sb.reverse().compareTo(new StringBuilder(c))==0 ;
    }
}
