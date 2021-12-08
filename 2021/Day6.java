import java.io.*;
import java.util.*;
public class Day6 {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day6input.txt");
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
    /*public static void solution(ArrayList<String> input) {
        //DAYS 80
        String[] strInput = input.get(0).split(",");
        
        ArrayList<Fish> fishes = new ArrayList<Fish>();
        for (int i = 0; i < strInput.length; i++) {
            fishes.add(new Fish(Integer.parseInt(strInput[i])));
            fishes.get(i).nextDay();
        }
        //SIM START:
        for (int i = 0; i < 256; i++) {
            if (i % 50 == 0) {
                int losl = 0;
            }
            for (int j = 0; j < fishes.size(); j++) {
                Fish baby = fishes.get(j).nextDay();
                int num = fishes.get(0).getNum();
                int lol = 0;
                if (baby != null) {
                    fishes.add(baby);
                }
            }
        }
        System.out.println("Num fish: "+fishes.get(0).getNum());
    }*/
        public static void solution(ArrayList<String> input) {
        //DAYS 80
        String[] strInput = input.get(0).split(",");
        
        long[] fishes = new long[9];
        for (int i = 0; i < strInput.length; i++) {
            fishes[Integer.parseInt(strInput[i])]++;
        }
        //SIM START:
        long[] temp = new long[9];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 9; j++) {
                temp[j] = fishes[j];
            }
            fishes[8] = temp[0];
            fishes[7] = temp[8];
            fishes[6] = temp[7]+temp[0];
            fishes[5] = temp[6];
            fishes[4] = temp[5];
            fishes[3] = temp[4];
            fishes[2] = temp[3];
            fishes[1] = temp[2];
            fishes[0] = temp[1];
        }
        
        long num = 0;
        for (int i = 0; i < 9; i++) {
            num += fishes[i];
        }
        System.out.println("Num fish: "+num);
    }
}
/*
class Fish {
    public static int num;
    private int days;
    private boolean created;
    public Fish(int numDays) {
        num++;
        days = numDays;
        created = true;
    }
    public Fish nextDay() {
        if (created)
            created = false;
        else {
            days--;
            if (days < 0) {
                days = 6;
                return new Fish(8);
            }
        }
        return null;
    }
    public int getNum() { return num; }
}
*/