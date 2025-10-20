package lab2.task4;

import java.util.*;

import lab2.Edge;
import lab2.Node;

public class UniformCostSearchAlgo {


	public Node execute(Node start, String goalLabel) {

		PriorityQueue<Node> frontier = new PriorityQueue<>(
				Comparator.comparingDouble(Node::getPathCost));

		Set<Node> explored = Collections.newSetFromMap(new IdentityHashMap<>());

		frontier.add(start);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			if (goalLabel.equals(current.getLabel())) {
				return current;
			}
			explored.add(current);
			for (Edge e : current.getChildren()) {
				Node child = e.getEnd();
				double newCost = current.getPathCost() + e.getWeight();

				// If not explored or in frontier
				if (!explored.contains(child) && !frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(newCost);
					frontier.add(child);
				}
				// If in frontier but with higher cost -> update
				else if (frontier.contains(child) && newCost < child.getPathCost()) {
					frontier.remove(child); // remove outdated version
					child.setParent(current);
					child.setPathCost(newCost);
					frontier.add(child);
				}
			}
		}
		return null; // not found
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		// First find the start node in the tree
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null; // Start node not found
		}

		// Reset path cost for start node
		startNode.setPathCost(0.0);
		startNode.setParent(null);

		// Then perform UCS from start node to find goal
		PriorityQueue<Node> frontier = new PriorityQueue<>(
				Comparator.comparingDouble(Node::getPathCost));

		Set<Node> explored = Collections.newSetFromMap(new IdentityHashMap<>());
		frontier.add(startNode);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			if (goal.equals(current.getLabel())) {
				return current;
			}
			explored.add(current);

			for (Edge e : current.getChildren()) {
				Node child = e.getEnd();
				double newCost = current.getPathCost() + e.getWeight();

				// If not explored or in frontier
				if (!explored.contains(child) && !frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(newCost);
					frontier.add(child);
				}
				// If in frontier but with higher cost -> update
				else if (frontier.contains(child) && newCost < child.getPathCost()) {
					frontier.remove(child); // remove outdated version
					child.setParent(current);
					child.setPathCost(newCost);
					frontier.add(child);
				}
			}
		}
		return null; // not found
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
