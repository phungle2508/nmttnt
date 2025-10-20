package lab4;

import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearchAlgo {

	private static class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			int c = Double.compare(o1.getH(), o2.getH());
			return (c != 0) ? c : o1.getLabel().compareTo(o2.getLabel());
		}
	}

	private static final Comparator<Node> BY_H_THEN_LABEL = Comparator.comparingDouble(Node::getH)
			.thenComparing(Node::getLabel);

	// Task 1.
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		frontier.add(root);
		Set<Node> explored = Collections.newSetFromMap(new IdentityHashMap<>());
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);

			for (Edge e : current.getChildren()) {
				Node child = e.getEnd();
				Double newH = current.getH() + child.getH();
				child.setParent(current);
				child.setH(newH);
				if (!explored.contains(child) || !frontier.contains(child)) {
					frontier.add(child);
				} else if (frontier.contains(child) && newH > current.getH()) {
					frontier.poll();
					frontier.add(child);
				}
			}
		}
		return null;
	}

	// Task 3.
	public Node execute(Node root, String start, String goal) {
		// TODO
		return null;
	}
}
