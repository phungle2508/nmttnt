package lab2.task1_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lab2.Node;

public class DepthFirstSearchAlgo {
	// Task 1.
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		List<Node> explored = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			for (Node node : current.getChildrenNodes()) {
				if (!explored.contains(node) && !frontier.contains(node)) {
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