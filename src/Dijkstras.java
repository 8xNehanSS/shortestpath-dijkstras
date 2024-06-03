/*
 * Dijkstras.java
 * Nehan Sudasinghe
 * 20220071 - w1998745
 */

import java.util.*;

/*
    * Dijkstras class to find the shortest path in a maze
    * This class is responsible for finding the shortest path in a maze using Dijkstra's algorithm
 */
abstract class Dijkstras {
    private static int visitedNodesCount = 0;
    public static LinkedList<Node> shortestPath(Maze puzzle) {
        Node start = puzzle.getStart();
        Node finish = puzzle.getFinish();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        start.distance = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.isVisited = true;
            visitedNodesCount++;
            if (node == finish) {
                break;
            }
            List<Node> nextNodes = getNextNodes(node, puzzle);
            for (Node nextNode : nextNodes) {
                int distance = node.distance + 1;
                if (distance < nextNode.distance && !nextNode.isVisited) {
                    nextNode.distance = distance;
                    nextNode.previous = node;
                    queue.add(nextNode);
                }
            }
        }
        if (!finish.isVisited) {
            return new LinkedList<>();
        }
        LinkedList<Node> path = new LinkedList<>();
        for (Node node = finish; node != null; node = node.previous) {
            path.addFirst(node);
        }
        return path;
    }

    /*
        * Method to get the next nodes of a given node
        * @param node the current node
        * @param puzzle the maze puzzle
        * @return List<Node> the next nodes of the given node
     */
    private static List<Node> getNextNodes(Node node, Maze puzzle) {
        Node[][] nodes = puzzle.getNodes();
        List<Node> nextNodes = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 4; i++) {
            int x = node.x;
            int y = node.y;
            while (true) {
                int newX = x + directions[i][0];
                int newY = y + directions[i][1];
                if (newX < 0 || newX >= nodes.length
                        || newY < 0 || newY >= nodes[0].length
                        || nodes[newX][newY].type == '0') {
                    break;
                }
                x = newX;
                y = newY;
                if (nodes[x][y].type == 'F') {
                    break;
                }
            }
            if (nodes[x][y] != node) {
                nextNodes.add(nodes[x][y]);
            }
        }
        return nextNodes;
    }

    public static int getVisitedNodesCount() {
        return visitedNodesCount;
    }
}
