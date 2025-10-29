package lab5;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.IdentityHashMap;

public class AStarSearchAlgo {
	// Task 3.
	public Node execute(Puzzle p) {
		return execute(p, 5000); // 30 second default limit
	}
	
	public Node execute(Puzzle p, long timeLimitMs) {
		Node root = p.getInitialState();
		Node goal = p.getGoalState();
		
		// Set heuristic and g values for root node
		root.setH(p.computeH2(root));
		root.setG(0);
		
		// A* uses F = G + H for comparison - using PuzzleUtils comparator
		PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);
		frontier.add(root);
		Set<Node> explored = Collections.newSetFromMap(new IdentityHashMap<>());
		
		long startTime = System.currentTimeMillis();
		long timeLimit = startTime + timeLimitMs;
		
		while (!frontier.isEmpty() && System.currentTimeMillis() < timeLimit) {
			Node current = frontier.poll();
			if (current.equals(goal)) {
				return current;
			}
			explored.add(current);

			// Get successors using puzzle's move operations
			for (Node child : p.getSuccessors(current)) {
				// Set heuristic and path cost
				child.setH(p.computeH2(child));
				child.setG(current.getG() + 1); // Each move costs 1
				
				// A* logic: add if not explored and not in frontier
				if (!explored.contains(child) && !frontier.contains(child)) {
					frontier.add(child);
				}
			}
		}
		return null;
	}
}
