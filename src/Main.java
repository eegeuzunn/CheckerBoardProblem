import Algorithms.GeneticAlgorithm;
import Algorithms.HillClimbingAlgorithm;
import Board.CheckerBoard;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.print("CREATED PUZZLE ");
        CheckerBoard checkerBoard = new CheckerBoard(20, 4);
        checkerBoard.randomizeBoard();
        checkerBoard.printBoard();

        System.out.println("\n ----------------------------------------------------------- \n");

        Long start = System.nanoTime();
        System.out.println("Hill Climbing Algorithm is running ");
        CheckerBoard solution = HillClimbingAlgorithm.solve(checkerBoard);
        solution.printBoard();
        Long end = System.nanoTime();
        System.out.println("Took " + ((end - start) / 1000000) + "ms to to complete");

        System.out.println("\n ----------------------------------------------------------- \n");

        start = System.nanoTime();
        System.out.println("Genetic Algorithm is running ");
        solution = GeneticAlgorithm.solve(checkerBoard);
        solution.printBoard();
        end = System.nanoTime();
        System.out.println("Took " + ((end - start) / 1000000) + "ms to to complete");


    }
}