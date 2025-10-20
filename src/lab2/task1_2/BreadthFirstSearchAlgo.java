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
		// First find the start node in the tree
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null; // Start node not found
		}

		// Then perform BFS from start node to find goal
		List<Node> explored = new ArrayList<>();
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(startNode);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);

			// Add children to frontier (BFS explores level by level)
			for (Node node : current.getChildrenNodes()) {
				if (!explored.contains(node)) {
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
