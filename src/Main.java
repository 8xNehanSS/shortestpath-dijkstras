/*
 * Main.java
 * Nehan Sudasinghe
 * 20220071 - w1998745
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * Main class to run the program
 * This class is responsible for reading the maze file name and solving the puzzle
 * It also prints the path and runtime of the solution
 */
public class Main {
    public static void main(String[] args) {
        System.out.print("Puzzle Solver\n\nChoose which puzzle to solve\n(Enter file name without .txt)\nInput Here: ");
        Scanner scanner = new Scanner(System.in);
        String choice;
        try {
             choice = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }
        solvePuzzle("mazes/"+choice + ".txt");
    }

    /*
        * Method to solve the puzzle
        * Reads the maze file and solves the puzzle
        * Prints the path and runtime of the solution
        * @param mazePath the path of the maze file
        * @return void
     */
    private static void solvePuzzle(String mazePath) {
        File file = new File(mazePath);
        if (!file.exists()) {
            System.out.println("Maze file not found");
            return;
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + file.getAbsolutePath());
            return;
        }
        System.out.println("\nSolving Maze: " + file.getName() + "\n");
        List<char[]> mazeList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");
            mazeList.add(line.toCharArray());
        }
        scanner.close();
        if (mazeList.isEmpty()) {
            System.out.println("No data in file: " + file.getAbsolutePath());
            return;
        }
        char[][] maze = mazeList.toArray(new char[0][]);

        Maze mazeObj = new Maze(maze);
        long startTime = System.currentTimeMillis();
        LinkedList<Node> path = Dijkstras.shortestPath(mazeObj);
        long endTime = System.currentTimeMillis();
        if (path.isEmpty()) {
            System.out.println("No valid path found.");
        } else {
            printPath(path, mazeObj.getStart());
            System.out.println("\nRuntime: " + (endTime - startTime) + "ms\n");
        }
        System.out.println("Number of vertices: " + mazeObj.getNumberOfVertices());
        System.out.println("Number of visited nodes: " + Dijkstras.getVisitedNodesCount());

    }

    private static void printPath(LinkedList<Node> path, Node mazeO) {
        System.out.println("1. Start at ("+(mazeO.x+1)+","+(mazeO.y+1)+")");
        List<Node> pathList = new ArrayList<>(path);

        for (int i = 1; i < pathList.size(); i++) {
            Node current = pathList.get(i);
            Node previous = pathList.get(i - 1);
            String direction = getDirection(previous, current);
            System.out.println((i+1) + ". Move " + direction + " to (" + (current.x + 1) + "," + (current.y + 1) + ")");
        }
        System.out.println(pathList.size()+1 + ". Done!");
    }

    private static String getDirection(Node from, Node to) {
        if (from.x == to.x) {
            return from.y < to.y ? "right" : "left";
        } else {
            return from.x < to.x ? "down" : "up";
        }
    }
}