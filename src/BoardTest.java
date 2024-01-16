import java.util.Arrays;

//Creating class for unit testing
class BoardTest {

    @org.junit.jupiter.api.Test
    void nextBoardState1() {
        int[][] firstBoard = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        int[][] expectedBoard = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        int[][] actualBoard = Board.nextBoardState(firstBoard);
        if(Arrays.deepEquals(actualBoard, expectedBoard)){
            System.out.println("Success!");
        } else {
            System.out.println("Test 1 failed!");
            System.out.println("Expected: ");
            System.out.println(Arrays.deepToString(expectedBoard));
            System.out.println("Actual: ");
            System.out.println(Arrays.deepToString(actualBoard));
        }
    }
    @org.junit.jupiter.api.Test
    void nextBoardState2() {
        int[][] firstBoard = {
                {0,0,1},
                {0,1,1},
                {0,0,0}
        };
        int[][] expectedBoard = {
                {0,1,1},
                {0,1,1},
                {0,0,0}
        };
        int[][] actualBoard = Board.nextBoardState(firstBoard);
        if(Arrays.deepEquals(actualBoard, expectedBoard)){
            System.out.println("Success!");
        } else {
            System.out.println("Test 1 failed!");
            System.out.println("Expected: ");
            System.out.println(Arrays.deepToString(expectedBoard));
            System.out.println("Actual: ");
            System.out.println(Arrays.deepToString(actualBoard));
        }
    }
}