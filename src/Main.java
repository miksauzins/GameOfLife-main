import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Game of Life!");
        Scanner scanner = new Scanner(System.in);
        boolean loadedFile;
        while(true) {
            System.out.println("Would you like to load in a starting board? Y - Yes, N - No");
            String loadAnswer = scanner.nextLine();
            if(loadAnswer.equalsIgnoreCase("Y")){
                loadedFile = true;
                break;
            } else if(loadAnswer.equalsIgnoreCase("N")){
                System.out.println("Generating random board..");
                loadedFile = false;
                break;
            } else {
                System.out.println("Not a valid answer. Try again. ");
            }
        }

        if(loadedFile){
            String directory;
            while(true) {
                System.out.println("Please enter the absolute directory of text file: ");
                directory = scanner.nextLine();
                if(!directory.toLowerCase().endsWith(".txt")){
                    System.out.println("Incorrect file format, please try again with a text file.");
                } else{
                    break;
                }
            }
            System.out.println("File loading..");
            int[][] board = Board.loadBoardFromTextFile(directory);
            System.out.println("Starting simulation, if you want to quit, press any key.");
            Board.runSimulation(board);
        } else {
            int height;
            int width;
            System.out.println("Enter random board height");
            while(true) {
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid input, try inputting a number again.");
                    scanner.nextLine();
                }else {
                    height = scanner.nextInt();
                    break;
                }
            }
            System.out.println("Enter board width: ");
            while(true) {
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid input, try inputting an Integer number again.");
                    scanner.nextLine();
                }else {
                    width = scanner.nextInt();
                    break;
                }
            }
            int[][] board = Board.random_state(height, width);
            System.out.println("Starting simulation in 3 seconds");
            Thread.sleep(3000);
            Board.runSimulation(board);
        }
    }
}