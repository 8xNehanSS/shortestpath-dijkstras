/*
 * Node.java
 * Nehan Sudasinghe
 * 20220071 - w1998745
 */

import java.util.ArrayList;
import java.util.List;

/*
 * Node class to represent a node in the maze
 * It stores the x and y coordinates of the node, the distance from the start node,
 * the previous node, the type of the node, the neighbors of the node, and whether the node is visited
 */
public class Node {
    protected int x, y;
    protected int distance;
    protected Node previous;
    protected char type;
    protected List<Node> neighbors;
    protected boolean isVisited;

    Node(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.neighbors = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
    }
}