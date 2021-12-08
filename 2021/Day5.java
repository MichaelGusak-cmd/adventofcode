import java.io.*;
import java.util.*;
public class Day5 {
    private static int[][] board = new int[1000][1000];
    public static void main(String[] args) {
        try {
            File myObj = new File("Day5input.txt");
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
        ArrayList<Line> TotalLines = new ArrayList<Line>();
        for (int i = 0; i < input.size(); i++) {
            TotalLines.add(new Line(input.get(i)));
        }
        //LINES CREATED
        ArrayList<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < input.size(); i++) {
            if (TotalLines.get(i).isVertOrHoriz()) {
                fillBoard(TotalLines.get(i).getLine());
            }
            else {
                fillBoardDiagonal(TotalLines.get(i).getLine());
            }
        }
        int count = countBoard();
        System.out.println("Count: "+count);
    }
    public static void fillBoard(int[] l) {
        if (l[0] == l[2]) {
            //[X][]
            int size = l[3] - l[1];
            int dir = 1;
            if (size < 0) { 
                size *= -1;
                dir = -1;
            }
            for (int i = 0; i <= size; i++) {
                int x = l[0];
                int y = (i*dir)+l[1];
                board[x][y]++;
            }
        }
        else {
            //[][Y]
            int size = l[2] - l[0];
            int dir = 1;
            if (size < 0) { 
                size *= -1;
                dir = -1;
            }
            for (int i = 0; i <= size; i++) {
                int x = (i*dir)+l[0];
                int y = l[1];
                board[x][y]++;
            }
        }
    }
    
    public static void fillBoardDiagonal(int[] l) {
        int up = 1;
        int right = 1;
        if (l[0] > l[2]) right = -1; 
        if (l[1] > l[3]) up = -1;
        int size = l[2]-l[0];
        if (size < 0) size*= -1;
        
        for (int i = 0; i <= size; i++) {
            int x = l[0] + i*right; 
            int y = l[1] + i*up;
            if (x == -1 || y == -1) 
                System.out.println();
            board[x][y]++;
        }
    }
    
    public static int countBoard() {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (board[i][j] >= 2)
                    count++;
            }
        }
        return count;
    }
}

class Line {
    int[] line;
    public Line(String input) { 
        line = new int[4];
        String[] inputPoints = input.split(" ");
        String[] startPoints = inputPoints[0].split(",");
        String[] endPoints = inputPoints[2].split(",");
        
        //START
        line[0] = Integer.parseInt(startPoints[0]);
        line[1] = Integer.parseInt(startPoints[1]);
        
        //END
        line[2] = Integer.parseInt(endPoints[0]);
        line[3] = Integer.parseInt(endPoints[1]);
    }
    
    int[] getLine() {
        return line;
    }
    
    boolean isVertOrHoriz() {
        if (line[0] == line[2] || line[1] == line[3])
            return true;
        return false;
    }
    boolean isHoriz() {
        if (line[0] == line[2])
            return true;
        return false;
    }
}
