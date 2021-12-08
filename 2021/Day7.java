import java.io.*;
import java.util.*;
public class Day7 {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day7input.txt");
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
        //most common to average?
        String[] strInput = input.get(0).split(",");
        int[] crabs = new int[strInput.length];
        int sum = 0;
        for (int i = 0; i < strInput.length; i++) {
            crabs[i] = Integer.parseInt(strInput[i]);
            sum+= crabs[i];
        }
        int average = sum/crabs.length;
        
        int cost = 0;
        long costs[] = new long[1000];
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < crabs.length; j++) {
                int n = (abs(((average-costs.length/2)+i)-crabs[j]));
                //costs[i] += n; //solution for Q1
                costs[i] += n*(n+1)/2; //solution for Q2
            }
        }
        
        long[] min = min(costs);
        System.out.println("Cost: "+min[0]+", Position: "+min[1]);
    }
    public static int abs(int num) {
        if (num < 0)
            num*=-1;
        return num;
    }
    public static long[] min(long[] arr) {
        long min = 1000000000;
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                pos = i;
            }
        }
        long[] out = new long[2];
        out[0] = min;
        out[1] = pos;
        return out;
    }
}
