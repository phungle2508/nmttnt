package lab2.task1_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab2.Node;

public class BreadthFirstSearchAlgo {
	// Task 1.
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		List<Node> explored = new ArrayList<>();
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			for (Node node : current.getChildrenNodes()) {
				if (!explored.contains(node) ) {
					node.setParent(current);
					frontier.add(node);
				}
			}
		}
		return null;
	}

	
	// Task 2.
	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		// TODO
		return null;
	}
}
