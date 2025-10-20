package lab2.task3;

import java.util.LinkedList;
import java.util.Queue;

import lab2.Node;

public class TreeBreadthFirstSearchAlgo {

	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			for (Node node : current.getChildrenNodes()) {
				node.setParent(current);
				frontier.add(node);
			}
		}
		return null;
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		// TODO
		return null;
	}
}
