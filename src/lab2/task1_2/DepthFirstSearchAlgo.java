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
		// First find the start node in the tree
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null; // Start node not found
		}

		// Then perform DFS from start node to find goal
		List<Node> explored = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(startNode);

		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);

			// Add children to frontier (DFS explores children in reverse order for stack)
			for (int i = current.getChildrenNodes().size() - 1; i >= 0; i--) {
				Node node = current.getChildrenNodes().get(i);
				if (!explored.contains(node) && !frontier.contains(node)) {
					node.setParent(current);
					frontier.add(node);
				}
			}
		}
		return null;
	}

	// Helper method to find a node with given label in the tree
	private Node findNode(Node root, String label) {
		if (root.getLabel().equals(label)) {
			return root;
		}

		Stack<Node> stack = new Stack<>();
		stack.add(root);

		while (!stack.isEmpty()) {
			Node current = stack.pop();
			for (Node child : current.getChildrenNodes()) {
				if (child.getLabel().equals(label)) {
					return child;
				}
				stack.add(child);
			}
		}
		return null;
	}
}