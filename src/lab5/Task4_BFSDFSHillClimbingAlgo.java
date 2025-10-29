package lab5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.PriorityQueue;

public class Task4_BFSDFSHillClimbingAlgo {
//	Task 4.1
	// run Breath first search
	public Node executeBFS(Puzzle p) {
		return executeBFS(p, 5000); // 30 second default limit
	}
	
	public Node executeBFS(Puzzle p, long timeLimitMs) {
		Node root = p.getInitialState();
		Node goal = p.getGoalState();
		
		// Set G for root node
		root.setG(0);
		
		List<Node> explored = new ArrayList<>();
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(root);
		
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
				child.setG(current.getG() + 1); // Each move costs 1
				if (!explored.contains(child) && !frontier.contains(child)) {
					frontier.add(child);
				}
			}
		}
		return null;
	}

//	Task 4.2
	// run Depth first search
	public Node executeDFS(Puzzle p) {
		return executeDFS(p, 5000); // 30 second default limit
	}
	
	public Node executeDFS(Puzzle p, long timeLimitMs) {
		Node root = p.getInitialState();
		Node goal = p.getGoalState();
		
		// Set G for root node
		root.setG(0);
		
		List<Node> explored = new ArrayList<>();
		Stack<Node> frontier = new Stack<>();
		frontier.add(root);
		
		long startTime = System.currentTimeMillis();
		long timeLimit = startTime + timeLimitMs;
		
		while (!frontier.isEmpty() && System.currentTimeMillis() < timeLimit) {
			Node current = frontier.pop();
			if (current.equals(goal)) {
				return current;
			}
			explored.add(current);
			
			// Get successors using puzzle's move operations
			// Add in reverse order for stack to maintain consistent exploration
			List<Node> successors = p.getSuccessors(current);
			for (int i = successors.size() - 1; i >= 0; i--) {
				Node child = successors.get(i);
				child.setG(current.getG() + 1); // Each move costs 1
				if (!explored.contains(child) && !frontier.contains(child)) {
					frontier.add(child);
				}
			}
		}
		return null;
	}

//	Task 4.3
	// run Hill climbing search
	public Node executeHillClimbing(Puzzle p) {
		return executeHillClimbing(p, 10000); // 10 second default limit
	}
	
	public Node executeHillClimbing(Puzzle p, long timeLimitMs) {
		Node current = p.getInitialState();
		Node goal = p.getGoalState();
		
		// Set initial values
		current.setG(0);
		current.setH(p.computeH2(current));
		
		long startTime = System.currentTimeMillis();
		long timeLimit = startTime + timeLimitMs;
		
		while (!current.equals(goal) && System.currentTimeMillis() < timeLimit) {
			// Get all successors
			List<Node> successors = p.getSuccessors(current);
			if (successors.isEmpty()) {
				break; // No moves available
			}
			
			// Calculate H for all successors
			for (Node child : successors) {
				child.setH(p.computeH2(child));
				child.setG(current.getG() + 1);
			}
			
			// Find best successor (lowest H value)
			Node bestChild = Collections.min(successors, PuzzleUtils.HeuristicComparatorByH);
			
			// If best child is not better than current, we're at local optimum
			if (bestChild.getH() >= current.getH()) {
				break;
			}
			
			// Move to the best child
			current = bestChild;
		}
		
		return current.equals(goal) ? current : null;
	}
}
