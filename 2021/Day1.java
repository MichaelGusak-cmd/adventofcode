import java.io.*; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//SOLUTION TO Q2
public class Day1 {
  public static void main(String[] args) {
    try {
      File myObj = new File("Day1input.txt");
      Scanner myReader = new Scanner(myObj);
      int prev = -1;
      int counter = 0;
      
      int drop = -1;
      int pop[] = new int[3];
      int windowCounter = 0;
      int window = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        int num = Integer.parseInt(data);
        if (windowCounter < 3) {
            windowCounter++;
            window+=num;
        }
        else {
            //NEW WINDOW, COMPARE WITH LAST
            prev = window;
            drop = pop[(windowCounter)%3];
            window = window - drop + num;
            windowCounter++;
            if (window > prev)
                counter++;
        }
        pop[(windowCounter-1)%3] = num;
      }
      System.out.println("Increased: "+counter+" times");
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

//SOLUTION TO Q1
/*public class Day1 {
  public static void main(String[] args) {
    try {
      File myObj = new File("Day1input.txt");
      Scanner myReader = new Scanner(myObj);
      int prev = -1;
      int counter = -1; //make sure prev is < first num
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        int num = Integer.parseInt(data);
        if (num > prev) {
            counter++;
        }
        prev = num;
      }
      System.out.println("Increased: "+counter+" times");
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}*/
