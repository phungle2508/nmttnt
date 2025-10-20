package lab2.task5;

import lab2.Node;
import lab2.NodeUtils;

public class Main {

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");
        Node nodeK = new Node("K");
        Node nodeL = new Node("L");
        Node nodeM = new Node("M");
        Node nodeN = new Node("N");
        Node nodeO = new Node("O");
        Node nodeP = new Node("P");
        Node nodeR = new Node("R");
        Node nodeS = new Node("S");

        // Build the tree
        nodeA.addEdge(nodeB, 1);
        nodeA.addEdge(nodeC, 1);
        nodeA.addEdge(nodeD, 1);

        nodeB.addEdge(nodeE, 1);
        nodeB.addEdge(nodeF, 1);

        nodeC.addEdge(nodeG, 1);
        nodeD.addEdge(nodeH, 1);

        nodeE.addEdge(nodeI, 1);
        nodeF.addEdge(nodeJ, 1);

        nodeF.addEdge(nodeK, 1);
        nodeG.addEdge(nodeL, 1);

        nodeH.addEdge(nodeM, 1);
        nodeH.addEdge(nodeN, 1);

        nodeK.addEdge(nodeO, 1);
        nodeK.addEdge(nodeP, 1);
        nodeL.addEdge(nodeR, 1);
        nodeN.addEdge(nodeS, 1);

        DepthLimitedSearchAlgo algo1 = new DepthLimitedSearchAlgo();
        Node result = algo1.execute(nodeA, "R", 4);
        System.out.println(NodeUtils.printPath(result));
    }

}
