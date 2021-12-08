import java.io.*;
import java.util.*;
public class Day3 {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day3input.txt");
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> allOxygen = new ArrayList<String>();
            ArrayList<String> allCarbon = new ArrayList<String>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allOxygen.add(data);
                allCarbon.add(data);
            }
            
            boolean stop = false;
            for (int i = 0; i < 12 && !stop; i++) {
                ArrayList<String> oxygen = new ArrayList<String>();
                ArrayList<String> carbon = new ArrayList<String>();
                int bitsO = 0;
                int bitsC = 0;
                if (allOxygen.size() > 1) {
                    for (int j = 0; j < allOxygen.size(); j++) {
                        if(allOxygen.get(j).charAt(i) == '1') {
                            bitsO++;
                        }
                        else
                            bitsO--;
                    }
                }
                if (allCarbon.size() > 1) {
                    for (int j = 0; j < allCarbon.size(); j++) {
                        if(allCarbon.get(j).charAt(i) == '1') {
                            bitsC++;
                        }
                        else
                            bitsC--;
                    }
                }
                if (allOxygen.size() > 1) {
                    if (bitsO >= 0) { //take all the 1's
                        for (int j = 0; j < allOxygen.size(); j++) {
                            if(allOxygen.get(j).charAt(i) == '1') {
                                oxygen.add(allOxygen.get(j));
                            }
                        }
                    } 
                    else {
                        for (int j = 0; j < allOxygen.size(); j++) {
                            if(allOxygen.get(j).charAt(i) == '0') {
                                oxygen.add(allOxygen.get(j));
                            }
                        }
                    }
                    allOxygen = oxygen;
                }
                if (allCarbon.size() > 1) {
                    if (bitsC >= 0) { //take all the 0s
                        for (int j = 0; j < allCarbon.size(); j++) {
                            if(allCarbon.get(j).charAt(i) == '0') {
                                carbon.add(allCarbon.get(j));
                            }
                        }
                    }
                    else {
                        for (int j = 0; j < allCarbon.size(); j++) {
                            if(allCarbon.get(j).charAt(i) == '1') {
                                carbon.add(allCarbon.get(j));
                            }
                        }
                    }
                    allCarbon = carbon;
                }
            }
            int o = Integer.parseInt(allOxygen.get(0), 2);
            int c = Integer.parseInt(allCarbon.get(0), 2);
            System.out.println("Oxygen: "+o+", Carbon: "+c+", Mult: "+(c*o));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
/*
import java.io.*; // Import this class to handle errors
import java.util.Scanner; 
public class Day3 {
public static void main(String[] args) {
try {
File myObj = new File("Day3input.txt");
Scanner myReader = new Scanner(myObj);
int nums[] = new int[12];
String gamma = "";
String epsilon = "";
while (myReader.hasNextLine()) {
String data = myReader.nextLine();
for (int i = 0; i < data.length(); i++) {
if (data.charAt(i) == '1') {
nums[i] += 1;
}
else
nums[i] -= 1;
}
}
for (int i = 0; i < nums.length; i++) {
if (nums[i] > 0) {
gamma += "1";
epsilon += "0";
}
else {
gamma += "0";
epsilon += "1";
}
}
System.out.println("Gamma: "+gamma+", Epsilon: "+epsilon);
int val = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
System.out.println("Multiplied: "+val);
myReader.close();
} catch (FileNotFoundException e) {
System.out.println("An error occurred.");
e.printStackTrace();
}
}
}
 */