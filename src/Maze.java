/*
 * Maze.java
 * Nehan Sudasinghe
 * 20220071 - w1998745
 */

/*
    * Maze class to represent the maze
    * This class is responsible for representing the maze
    * It stores the nodes of the maze and the start and finish nodes
 */
public class Maze {
    private Node[][] nodes;
    private Node start, finish;
    public Node getStart() {
        return start;
    }
    public Node getFinish() {
        return finish;
    }
    public Node[][] getNodes() {
        return nodes;
    }
    public Maze(char[][] maze) {
        if (maze.length == 0 || maze[0].length == 0) {
            throw new IllegalArgumentException("Maze cannot be empty");
        }
        nodes = new Node[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                nodes[i][j] = new Node(i, j, maze[i][j]);
                if (maze[i][j] == 'S') {
                    start = nodes[i][j];
                } else if (maze[i][j] == 'F') {
                    finish = nodes[i][j];
                }
            }
        }
    }
    public int getNumberOfVertices() {
        return nodes.length * nodes[0].length;
    }
}