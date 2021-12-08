import java.io.*; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Day2 {
  public static void main(String[] args) {
    try {
      File myObj = new File("Day2input.txt");
      Scanner myReader = new Scanner(myObj);
      int aim = 0;
      int depth = 0;
      int horiz = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String d[] = data.split(" ");
        if (d[0].equals("forward")) {
            horiz += Integer.parseInt(d[1]);
            depth += Integer.parseInt(d[1])*aim;
        }
        else if (d[0].equals("up")) {
            aim -= Integer.parseInt(d[1]);
        }
        else {
            aim += Integer.parseInt(d[1]);
        }
      }
      int mult = horiz*depth;
      System.out.println("Depth: "+depth+", Horizontal: "+horiz+", multiplied: "+mult);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

//Solution to Q1
/*import java.io.*; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Day2 {
  public static void main(String[] args) {
    try {
      File myObj = new File("Day2input.txt");
      Scanner myReader = new Scanner(myObj);
      int depth = 0;
      int horiz = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String d[] = data.split(" ");
        if (d[0].equals("forward")) {
            horiz += Integer.parseInt(d[1]);
        }
        else if (d[0].equals("up")) {
            depth -= Integer.parseInt(d[1]);
        }
        else {
            depth += Integer.parseInt(d[1]);
        }
      }
      int mult = horiz*depth;
      System.out.println("Depth: "+depth+", Horizontal: "+horiz+", multiplied: "+mult);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
*/