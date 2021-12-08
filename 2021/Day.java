import java.io.*;
import java.util.*;
public class Day {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day1input.txt");
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> input = new ArrayList<String>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            solution(input);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void solution(ArrayList<String> input) {
        
    }
}
