import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.junit.Test;

public class ByteManipulator {
    @Test
    public void playBytes(){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        byte[] alphaBytes = alpha.getBytes(StandardCharsets.US_ASCII);
        byte[] test = new byte[10];
        System.out.println(alphaBytes[0]);
        test[0] = (byte) 'a';
        test[0]+= (byte) 'c';
        test[1] = (byte) 97;
        System.out.println(Arrays.toString(test));
        //System.out.println(Byte.toString(test[1]));
        //test[0] -= (byte) 'c';
        String bytesToString = new String(test, StandardCharsets.UTF_8);
        System.out.println(bytesToString);
    }

    @Test
    public void manipulateBytes() {
        String inputFile = "/Users/grimm/IdeaProjects/ByteManipulator/data/test-1.txt";
        File targetFile = new File(inputFile);
        try {

            byte[] fileBytes = getBytes(targetFile);
            byte[] outputBytes = new byte[fileBytes.length];
            System.out.println("File size:" + fileBytes.length);
            int position = fileBytes.length - 1;
            Stack<Integer> listOfIndicies = new Stack<Integer>();
            for(int i = 0; i < fileBytes.length; i++){
                if(i % 2 == 0){
                    if(i % fileBytes.length == 0) {
                        System.out.println("I:" + i);
                        outputBytes[i] = fileBytes[i];
                    } else {
                        listOfIndicies.push(i);
                    }
                } else if(i % 3 == 0){
                    outputBytes[position] = fileBytes[i];
                    position--;
                } else{
                    listOfIndicies.push(i);
                }
            }
            while(!listOfIndicies.isEmpty()){
                int curIndex = listOfIndicies.pop();
                if(listOfIndicies.isEmpty()){
                    outputBytes[curIndex] = fileBytes[curIndex];
                } else {
                    int nextIndex = listOfIndicies.pop();
                    outputBytes[nextIndex] = fileBytes[curIndex];
                    outputBytes[curIndex] = fileBytes[nextIndex];
                }
            }
            String fileName = targetFile.getName().replace(".txt", "Out.txt");
            File outputFile = new File(fileName);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            outputStream.close();
            //outputFile.createNewFile();
        } catch(IOException e){
            System.out.println("IOException thrown. Could not manipulate ByteStream.");
        }

    }

    public static byte[] getBytes(File inFile) throws IOException {
        ByteArrayOutputStream fileByteOutputStream = new ByteArrayOutputStream();
        FileInputStream fileStream = new FileInputStream(inFile);
        byte[] buffer = new byte[2048];
        int read;
        while((read = fileStream.read(buffer)) != -1){
            fileByteOutputStream.write(buffer);
        }
        return fileByteOutputStream.toByteArray();


    }
}
