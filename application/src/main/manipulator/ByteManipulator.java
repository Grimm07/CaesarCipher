package main.manipulator;
import java.io.*;
import java.util.*;

public class ByteManipulator {
    public static void manipulateBytes(String fileName) {
        File targetFile = new File(fileName);
        try {

            byte[] fileBytes = getBytes(targetFile);
            byte[] outputBytes = new byte[fileBytes.length];
            System.out.println("File size:" + fileBytes.length);
            int position = fileBytes.length - 1;
            Stack<Integer> listOfIndicies = new Stack<Integer>();
            for(int i = 0; i < fileBytes.length; i++){
                if(i % 2 == 0){
                    System.out.println("byte is even");
                    if(i % fileBytes.length == 0) {
                        System.out.println("I:" + i);
                        outputBytes[i] = fileBytes[i];
                    } else {
                        listOfIndicies.push(i);
                    }
                } else if(i % 3 == 0){
                    outputBytes[position] = fileBytes[i];
                    position --;
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
