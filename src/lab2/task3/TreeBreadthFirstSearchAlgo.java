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
		// First find the start node in the tree
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null; // Start node not found
		}

		// Then perform BFS from start node to find goal
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(startNode);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}

			// Add children to frontier (Tree BFS doesn't need cycle detection)
			for (Node node : current.getChildrenNodes()) {
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

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (Node child : current.getChildrenNodes()) {
				if (child.getLabel().equals(label)) {
					return child;
				}
				queue.add(child);
			}
		}
		return null;
	}
}
