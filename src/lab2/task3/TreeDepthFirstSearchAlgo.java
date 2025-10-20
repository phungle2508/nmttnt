package lab2.task3;

import java.util.Stack;

import lab2.Node;

public class TreeDepthFirstSearchAlgo {
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
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
		// First find the start node in the tree
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null; // Start node not found
		}

		// Then perform DFS from start node to find goal
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(startNode);

		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			}

			// Add children to frontier (Tree DFS doesn't need cycle detection)
			for (int i = current.getChildrenNodes().size() - 1; i >= 0; i--) {
				Node node = current.getChildrenNodes().get(i);
				node.setParent(current);
				frontier.add(node);
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