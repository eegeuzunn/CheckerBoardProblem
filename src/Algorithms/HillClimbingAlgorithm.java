package Algorithms;

import Board.CheckerBoard;

public class HillClimbingAlgorithm {

    public static CheckerBoard solve(CheckerBoard board){

        int bestConflict = board.calculateNumberOfConflicts();

        for(int i = 0; i < board.getN();  i++){
            for (int j = 0; j< board.getN(); j++){

                if(bestConflict == 0) return board;

                int originalColor = board.getBoard()[i][j];
                for(int color = 0; color < board.getNumberOfColors(); color++){
                    if(color == originalColor){
                        continue;
                    } else {
                        board.getBoard()[i][j] = color;
                    }

                    if(bestConflict > board.calculateNumberOfConflicts()){
                        bestConflict = board.calculateNumberOfConflicts();
                        break;
                    }else {
                        board.getBoard()[i][j] = originalColor;
                    }
                }
            }
        }

        System.out.println("Could not solve, best solution is...");
        return board;
    }

}
