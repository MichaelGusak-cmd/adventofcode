import java.io.*;
import java.util.*;
public class Day8 {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day8input.txt");
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
    /**
     * THINKING THINGS THROUGH:
     *   0:      1:      2:      3:      4:
         aaaa    ....    aaaa    aaaa    ....
        b    c  .    c  .    c  .    c  b    c
        b    c  .    c  .    c  .    c  b    c
         ....    ....    dddd    dddd    dddd
        e    f  .    f  e    .  .    f  .    f
        e    f  .    f  e    .  .    f  .    f
         gggg    ....    gggg    gggg    ....
        
          5:      6:      7:      8:      9:
         aaaa    aaaa    aaaa    aaaa    aaaa
        b    .  b    .  .    c  b    c  b    c
        b    .  b    .  .    c  b    c  b    c
         dddd    dddd    ....    dddd    dddd
        .    f  e    f  .    f  e    f  .    f
        .    f  e    f  .    f  e    f  .    f
         gggg    gggg    ....    gggg    gggg
         
         0: abcefg
         1:   c  f
         2: a cde g
         3: a cd fg
         4:  bcd f
         5: ab d fg
         6: ab defg
         7: a c  f
         8: abcdegf
         9: abcd fg
         
         Lengths:
         0 == 6 == 9 == abfg
         1
         2 == 3 == 5
         4
         7
         8
         
         1: cf
         4: cf db
         7: cf a
         
         1: cf -> 4: bd 
         1: cf -> 7: a 
         
         2:acdf -> e
         2 if len(5) && doesn't share unknown with others (3&5)
     */
    public static void solution(ArrayList<String> input) {
        String[] strInput = new String[input.size()];
        for (int i = 0; i < strInput.length; i++) {
            strInput[i] = input.get(i);
        }
        long sum = 0;
        for (int i = 0; i < strInput.length; i++) {
            String[] output = strInput[i].split("&");
            String[] solved = solver(output[0].split(" "));
            int decoded = decoder(solved, output[1].split(" "));
            sum+=decoded;
        }
        System.out.println("Sum: "+sum);
    }
    public static String[] solver(String[] arr) {
        String[] solution = new String[10];
        for (int i = 0; i < solution.length; i++)
            solution[i] = "";
        for (int i = 0; i < arr.length; i++)
            arr[i] = sortStr(arr[i]);
        boolean solved = false;
        int pos = 0;
        
        /**
         * vals[0] = "cf"
         * vals[1] = "bd"
         * vals[2] = "g"
         * vals[3] = "a"
         * vals[4] = "e"
         */
        String[] vals = new String[5];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = "";
        }
        while (!solved) {
            if (arr[pos].length() == 2) { //found 1
                solution[1] = arr[pos];
                vals[0] = arr[pos];
            }
            else if (arr[pos].length() == 3) { //found 7
                solution[7] = arr[pos];
                if (vals[0].length() > 0 && vals[3].length() == 0)
                    vals[3] = strNotIn(arr[pos], vals[0]);
            }
            else if (arr[pos].length() == 4) { //found 4
                solution[4] = arr[pos];
                if (vals[0].length() > 0 && vals[1].length() == 0) {
                    vals[1] = strNotIn(arr[pos], vals[0]);
                }
            }
            else if (arr[pos].length() == 6) { //MAYBE 9?
                if (vals[0].length() > 0 && vals[1].length() > 0 && vals[3].length() > 0 && vals[2].length() == 0) {
                    vals[2] = strNotIn(arr[pos], vals[0]+vals[1]+vals[3]);
                    if (sortStr(vals[3]+vals[2]+vals[0]+vals[1]).equals(arr[pos]))
                        solution[9] = arr[pos];
                    else
                        vals[2] = "";
                }
            }
            else if (arr[pos].length() == 7) { //found 8
                solution[8] = arr[pos];
                boolean foundE = vals[4].length() > 0;
                boolean check = true;
                String sumString = "";
                for (int i = 0; i < vals.length-1 && !foundE; i++) {
                    sumString += vals[i];
                    if (vals[i].length() == 0)
                        check = false;
                }
                if (check && !foundE) {
                    vals[4] = strNotIn(arr[pos], sumString);
                    foundE = true;
                    solved = true;
                }
            }
            pos = (pos+1)%10;
        }
        
        //new while loop so as to now mess with the other values, and as a
        //gurantee that the vals[] arr is filled without having to check every damn time
        solved = false;
        while (!solved) {
            if (arr[pos].length() == 6) {
                if (!arr[pos].equals(solution[9])) {
                    if (includes(arr[pos],vals[0]))
                        solution[0] = arr[pos];
                    else
                        solution[6] = arr[pos];
                }
            }
            if (arr[pos].length() == 5) {
                if (includes(arr[pos],vals[0])) {
                    solution[3] = arr[pos];
                }
                else if (includes(arr[pos], vals[4])) {
                    solution[2] = arr[pos];
                }
                else
                    solution[5] = arr[pos];
            }
            pos = (pos+1)%10;
            boolean done = true;
            for (int i = 0; i < solution.length; i++) {
                if (solution[i].length() == 0)
                    done = false;
            }
            if (done)
                solved = true;
        }
        return solution;
    }
    public static boolean includes(String input, String check) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < check.length(); j++) {
                if (input.charAt(i) == check.charAt(j)) {
                    counter++;
                }
            }
        }
        return (counter == check.length());
    }
    public static String strNotIn(String input, String exclude) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            boolean include = true;
            for (int j = 0; j < exclude.length(); j++) {
                if (input.charAt(i) == exclude.charAt(j)) {
                    include = false;
                }
            }
            if (include)
                output += input.charAt(i);
        }
        return sortStr(output);
    }
    public static int decoder(String[] sol, String[] val) {
        String decoded = "";
        for (int i = 0; i < val.length; i++) {
            String sorted = sortStr(val[i]);
            for (int j = 0; j < sol.length; j++) {
                if (sorted.equals(sol[j])) {
                    decoded += j;
                }
            }
        }
        return Integer.parseInt(decoded);
    }
    public static String sortStr(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
//SOLUTION FOR Q1
/*
   public static void solution(ArrayList<String> input) {
        String[] strInput = new String[input.size()];
        for (int i = 0; i < strInput.length; i++) {
            strInput[i] = input.get(i);
        }
        System.out.println(strInput[0]);
        int count = 0;
        for (int i = 0; i < strInput.length; i++) {
            String[] output = strInput[i].split("&");
            String[] outputs = output[1].split(" ");
            for (int j = 0; j < outputs.length; j++) {
                int s = outputs[j].length();
                if (s == 2 || s == 4 || s == 3 || s == 7)
                    count++;
            }
        }
        System.out.println("Count: "+count);
    }
 */
