package test;
import main.manipulator.ByteManipulator;
import org.junit.Test;

public class fileLoader {
    @Test
    public static void runFile(){
        String filePath = "/Users/grimm/IdeaProjects/ByteManipulator/data/test.txt";
        ByteManipulator.manipulateBytes(filePath);
    }
}
