import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
// Method to generate a board with randomly generated alive and dead states(1 or 0)
    public static int[][] random_state(int height, int width){
        int[][] board = dead_state(height, width);
        for(int x = 0; x < height; x++){
            for(int y= 0; y < width;y++){
                double random_number = Math.random();
                if(random_number > 0.5){
                    board[x][y] = 1;
                }
            }
        }
        return board;
    }
//    Method to generate a board with all values set to 0(or dead) given a width and height for the board.
    public static int[][] dead_state(int height, int width){
        int[][] board = new int[height][width];
        for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
                board[x][y] = 0;
            }
        }
        return board;
    }
//  Method to print out the board with a border around it.
    public static void renderBoard(int[][] board){

        boolean firstLine = false;
        boolean lastLine = false;
        int length = board.length + 2;
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(x == 0 && !firstLine){
                        for(int i = 0; i < board[x].length + 2; i++){
                            System.out.print("-");
                        }
                        firstLine = true;
                        System.out.println();
                    }
                if(y == 0){
                    System.out.print("|");
                    if(board[x][y] == 1){
                        System.out.print("#");
                    }else {
                        System.out.print(" ");
                    }
                }
                else if (y + 1 == board[x].length) {
                    if(board[x][y] == 1){
                        System.out.print("#");
                    }else {
                        System.out.print(" ");
                    }
                    System.out.print("|");
                    if(x + 1 == board.length){
                        lastLine = true;
                    }
                }
                else {
                    if(board[x][y] == 1){
                        System.out.print("#");
                    }else {
                        System.out.print(" ");
                    }
                }
                if(x + 1 == board.length && lastLine){
                    System.out.println();
                    for(int i = 0; i < board[x].length + 2; i++){
                        System.out.print("-");
                    }
                    lastLine = true;
                }
            }
            System.out.println();
        }
    }

//   Method to calculate the next board state, checking for neighbour count and using that
//   to determine whether a certain cell should stay alive or dead.
//   Any live cell with 0 or 1 live neighbors becomes dead, because of underpopulation
//   Any live cell with 2 or 3 live neighbors stays alive, because its neighborhood is just right
//   Any live cell with more than 3 live neighbors becomes dead, because of overpopulation
//   Any dead cell with exactly 3 live neighbors becomes alive, by reproduction
    public static int[][] nextBoardState(int[][] board){
        int[][] newBoard = dead_state(board.length, board[0].length);
        int height = board.length;
        int width = board[0].length;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int neighbourCount = 0;
                for(int x = -1; x < 2; x++){
                    int currentx = i + x;
                    for(int y = -1; y < 2; y++){
                        int currenty = j + y;
                        if(currentx == i && currenty == j){
                            continue;
                        }
                        if(currentx > -1 && currentx <= (height - 1) && currenty > -1 && currenty <= (width - 1) ){
                            if(board[currentx][currenty] == 1){
                                neighbourCount++;
                            }
                        }
                    }
                }
                if(board[i][j] == 1 && (neighbourCount == 0 || neighbourCount == 1)){
                    newBoard[i][j] = 0;
                } else if(board[i][j] == 1 && (neighbourCount == 2 || neighbourCount == 3)){
                    newBoard[i][j] = 1;
                } else if(board[i][j] == 1 && (neighbourCount > 3)){
                    newBoard[i][j] = 0;
                } else if(board[i][j] == 0 && neighbourCount == 3){
                    newBoard[i][j] = 1;
                }
            }
        }
        return newBoard;
    }

//  Method to run the simulation of the Game of Life, which also checks if the board stays exactly the same
//  between iterations, in which case, the game ends.
    public static void runSimulation(int[][] board) throws Exception{
        int iterations = 1;
        Scanner scanner = new Scanner(System.in);
        boolean loadNext = true;
        while(loadNext){
            System.out.println("Current iteration: " + iterations);
            Board.renderBoard(board);
            int[][] prevBoard = board;
            board = Board.nextBoardState(board);
            if(Arrays.deepEquals(prevBoard,board)){
                System.out.println("Loop ended at " + iterations + " iterations");
                break;
            }
            System.out.println("Would you like to load in the next board? Y - Yes, N - No");
            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("N")){
                System.out.println("You stopped at " + iterations + " iterations");
                break;
            } else{
            Thread.sleep(500);
            iterations++;
        }
        }
}

//  Method used to load in a board from a text file, which contains 0's and 1's.
//  this method could be improved upon, as it opens 2 readers, for the same file,
//  but I couldn't get the mark() and reset() functions for BufferedReader to work properly.
    public static int[][] loadBoardFromTextFile(String directory) throws Exception{
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(directory));
        if(reader == null){
            System.out.println("Error occured while trying to load the file.");
            return null;
        }
        int lines = 1;
        int lineLength = reader.readLine().length();
        while(reader.readLine() != null){
            lines++;
        }
        reader.close();
        reader = new BufferedReader(new FileReader(directory));
        int[][] board = new int[lines][lineLength];
        for(int i = 0; i < lines; i++){
            String input = reader.readLine();
            for(int j = 0; j < lineLength; j++){
                board[i][j] = (int) input.charAt(j) - 48;
            }
        }
        reader.close();
        return board;
    }
}
