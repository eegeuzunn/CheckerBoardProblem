package Board;

import java.util.Random;

public class CheckerBoard {
    private int n;
    private int[][] board;
    private int numberOfColors;
    private int conflict;
    public CheckerBoard(int n, int numberOfColors){
        this.n = n;
        this.numberOfColors = numberOfColors;
        board = new int[n][n];
    }

    public void randomizeBoard(){
        Random random = new Random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = random.nextInt(numberOfColors);
            }
        }
        conflict = calculateNumberOfConflicts();
    }

    public void printBoard(){

        System.out.print("BOARD :\n");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.print("\n");
        }

    }

    public int calculateNumberOfConflicts(){
        int conflict = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i < n-1 && board[i][j] == board[i+1][j]) {
                    conflict++;
                }
                if(j < n-1 && board[i][j] == board[i][j+1]){
                    conflict++;
                }
            }
        }
        return conflict;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getNumberOfColors() {
        return numberOfColors;
    }

    public void setNumberOfColors(int numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getConflict() {
        return conflict;
    }

    public void setConflict(int conflict) {
        this.conflict = conflict;
    }
}
