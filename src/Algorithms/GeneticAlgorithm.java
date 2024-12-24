package Algorithms;

import Board.CheckerBoard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    /*
        Lecture 6 slide 66 dan alındı.
        Steps:
        1-Choose initial population
	    2-Evaluate the fitness of each individual in the population
        3-Repeat
        4-Select best-ranking individuals to reproduce
        5-Breed new generation through crossover and mutation (genetic operations) and give birth to offspring
        6-Evaluate the individual fitnesses of the offspring
        7-Replace worst ranked part of population with offspring
        8-Until <terminating condition>
     */

    public static CheckerBoard solve(CheckerBoard board){
        int n = board.getN();
        int colors = board.getNumberOfColors();

        int populationLimit = 100;
        int elitePopulationLimit = 5;
        float crossoverPoint = 0.3f;
        int numberOfMutation = 3;

        List<CheckerBoard> population = new ArrayList<>();
        population.add(board);

        //1-2 :this is initial population.
        //Fitness is number of conflicts and they are calculated inside of class when we run randomize board.
        for(int i = 0; i < populationLimit-1; i++){
            CheckerBoard randomNewBoard = new CheckerBoard(n, colors);
            randomNewBoard.randomizeBoard();
            population.add(randomNewBoard);
        }

        boolean isSolution = false;
        // 3- Repeat
        while(!isSolution){

            population.sort(new Comparator<CheckerBoard>(){
                @Override
                public int compare(CheckerBoard o1, CheckerBoard o2) {
                    return Integer.compare(o1.getConflict(), o2.getConflict());
                }
            });
            //Terminating Condition
            if(population.get(0).calculateNumberOfConflicts() == 0) {
                isSolution= true;
                return population.get(0);
            }
            List<CheckerBoard> newGeneration = new ArrayList<>();

            //Add directly elite generations to new generation
            //i took 5 of them, may want to increase
            for( int i = 0; i < elitePopulationLimit; i++){
                newGeneration.add(population.get(i));
            }

            Random random = new Random();
            //5-6 Breeding
            for(int i = 0; i < populationLimit - elitePopulationLimit; i++){
                CheckerBoard newChild = crossover(
                        population.get(random.nextInt(population.size())),
                        population.get(random.nextInt(population.size())),
                        crossoverPoint
                );
                mutation(newChild, numberOfMutation);
                newGeneration.add(newChild);
            }
            
            population = newGeneration;
        }

        return null;
    }

    private static void mutation(CheckerBoard newChild, int numberOfMutation) {
        Random random = new Random();
        for( int i = 0; i< numberOfMutation; i++){
            int x = random.nextInt(newChild.getN());
            int y = random.nextInt(newChild.getN());
            newChild.getBoard()[x][y] = random.nextInt(newChild.getNumberOfColors());
        }
        newChild.setConflict(newChild.calculateNumberOfConflicts());
    }

    private static CheckerBoard crossover(CheckerBoard checkerBoard1, CheckerBoard checkerBoard2, float crossOverPoint) {
        int n = checkerBoard1.getN();
        int numberOfNodes = n*n;

        int counter = 0;
        int limit = Math.round(numberOfNodes * crossOverPoint);

        int[][] board = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(counter < limit){
                    board[i][j] = checkerBoard1.getBoard()[i][j];
                    counter ++;
                } else {
                    board[i][j] = checkerBoard2.getBoard()[i][j];
                }
            }
        }
        CheckerBoard child = new CheckerBoard(n, checkerBoard1.getNumberOfColors());
        child.setBoard(board);
        child.setConflict(child.calculateNumberOfConflicts());
        return child;
    }

}
