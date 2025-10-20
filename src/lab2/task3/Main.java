package lab2.task3;

import lab2.Node;
import lab2.NodeUtils;

public class Main {
    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);
        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);
        nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2);
        nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6);
        nodeF.addEdge(nodeG, 1);
        TreeBreadthFirstSearchAlgo algo1 = new TreeBreadthFirstSearchAlgo();
        Node result = algo1.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));

        TreeDepthFirstSearchAlgo algo2 = new TreeDepthFirstSearchAlgo();
        Node result2 = algo2.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result2));

    }
}
