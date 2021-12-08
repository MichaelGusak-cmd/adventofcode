import java.io.*;
import java.util.*;
public class Day4 {
    public static void main(String[] args) {
        try {
            File myObj = new File("Day4input.txt");
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
        String[] strDraws = input.get(0).split(",");
        int[] draws = new int[strDraws.length];
        for (int i = 0; i < strDraws.length; i++) {
            draws[i] = Integer.parseInt(strDraws[i]);
        }
        
        ArrayList<Board> boards = new ArrayList<Board>();
        ArrayList<String> tempBoard = new ArrayList<String>();
        int counter = 0;
        for (int i = 1; i < input.size()-1; i++) {
            tempBoard.add(input.get(i+1));
            counter++;
            if (counter %5 == 0) {
                boards.add(new Board(tempBoard));
                tempBoard.clear();
                i++;
            }
        }
        
        //LETS PLAY!
        int[][] win = new int[5][5];
        int winDraw = -1;
        boolean stop = false;
        for (int i = 0; i < draws.length && !stop; i++) {
            int numWins = 0;
            for (int j = 0; j < boards.size() && !stop; j++) {
                boards.get(j).drew(draws[i]);
                if (boards.get(j).isWin()) {
                    //stop = true; REMOVED FOR Q2
                    if (boards.size() > 1) {
                        boards.remove(j);
                        j--;
                    }
                    else {
                        stop = true;
                        win = boards.get(j).getBoard();
                        winDraw = draws[i];
                    }   
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sum += win[i][j];
            }
        }
        System.out.println("Winning draw: "+winDraw+", Sum: "+sum+", Mult: "+ (winDraw*sum));
    }
}
/**
 * Interface:
 * Board(ArrayList<String> data)
 * int[][] getBoard()
 * boolean isWin()
 * void drew(int n)
 */
class Board {
    int[][] board;
    boolean[][] drawn;
    public Board(ArrayList<String> data) {
        board = new int[5][5];
        drawn = new boolean[5][5];
        int boardPos = 0;
        for (int i = 0; i < 5; i++) {
            String[] row = data.get(i).split(" ");
            for (int j = 0; j < row.length; j++) {
                if (!row[j].equals("")){
                    board[i][boardPos] = Integer.parseInt(row[j]); 
                    boardPos++;
                }
            }
            boardPos = 0;
        }
    }
    
    public int[][] getBoard() {
        //RETURNS ALL THE UNMARKED NUMBERS (ALL MARKED NUMS ARE SET TO 0)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (drawn[i][j]) 
                    board[i][j] = 0;
            }
        }
        return board;
    }
    
    public boolean isWin() {
        //check if any row/column in drawn is filled
        for (int i = 0; i < 5; i++) {
            boolean r = checkRow(i);
            boolean c = checkCol(i);
            if (r || c) {
                return true;
            }
        }
        return false;
    }
    
    public void drew(int n) {
        boolean stop = false; //if no duplicate numbers in a board
        for (int i = 0; i < 5 && !stop; i++) {
            for (int j = 0; j < 5 && !stop; j++) {
                if (board[i][j] == n) {
                    drawn[i][j] = true;
                    stop = true;
                }
            }
        }
    }
    
    private boolean checkRow(int i) {
        return (drawn[0][i] && drawn[1][i] && drawn[2][i] && drawn[3][i] && drawn[4][i]);
    }
    private boolean checkCol(int j) {
        return (drawn[j][0] && drawn[j][1] && drawn[j][2] && drawn[j][3] && drawn[j][4]);
    }
}