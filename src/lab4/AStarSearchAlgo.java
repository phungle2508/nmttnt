package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearchAlgo {
	// Task 2.
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(n -> n.getG() + n.getH()));
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
				Double newG = current.getG() + (e.getWeight());
				child.setParent(current);
				child.setG(newG);
				if (!explored.contains(child) || !frontier.contains(child)) {
					frontier.add(child);
				} else if (frontier.contains(child) && newG > current.getG()) {
					frontier.remove(child);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	// Task 3.
	public boolean isAdmissibleH(Node root, String goal) {
		Double hn = 0.0;
		Node result = execute(root, goal);
		Node tmp;
		while ((tmp = result.getParent()) != null) {
			hn += tmp.getH();
			result = tmp;
			if (tmp.getH() >= 0 && tmp.getH() <= hn) {
			}else{
				return false;
			}
		}
	
		return true;
	}

	// Task 5
	public Node execute(Node root, String start, String goal) {
		// TODO
		return null;
	}
}
