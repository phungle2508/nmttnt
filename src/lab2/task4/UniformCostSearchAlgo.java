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
		// TODO
		return null;
	}
}
