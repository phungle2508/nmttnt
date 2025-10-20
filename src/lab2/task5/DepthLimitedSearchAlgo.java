package lab2.task5;

import lab2.Edge;
import lab2.Node;

public class DepthLimitedSearchAlgo {
	private static final Node CUTOFF = new Node("cutoff");
	private static final Node FAILURE = new Node("failure");

	public Node depthLimitedSearch(Node root, String goal, int limit) {
		return recursive_DLS(root, goal, limit);
	}

	public Node recursive_DLS(Node node, String goal, int limit) {
		if (node.getLabel().equals(goal)) {
			return node;

		} else if (limit == 0)
			return CUTOFF;
		else {
			boolean cutoff_occurred = false;
			for (Edge e : node.getChildren()) {
				Node child = e.getEnd();
				child.setParent(node);
				Node result = recursive_DLS(child, goal, limit - 1);
				if (result == CUTOFF) {
					cutoff_occurred = true;
				} else if (result != FAILURE) {
					return result;
				}
			}
			if (cutoff_occurred) {
				return CUTOFF;
			} else {
				return FAILURE;
			}
		}
	}

	// if result == cutoff then cutoff_occurred? return true
	// else if result is not failure then return result
	// if cutoff_occurred? then return cutoff else return failure
	public void execute(Node tree, int maxDepth) {
		// This method could be used to explore all nodes up to maxDepth
		// For now, it's a placeholder method that doesn't return anything
		// Could be used to print all reachable nodes within depth limit
		exploreUpToDepth(tree, maxDepth, 0);
	}

	// Helper method to explore all nodes up to a certain depth
	private void exploreUpToDepth(Node node, int maxDepth, int currentDepth) {
		if (currentDepth > maxDepth) {
			return;
		}

		System.out.println("Depth " + currentDepth + ": " + node.getLabel());

		for (Edge e : node.getChildren()) {
			Node child = e.getEnd();
			exploreUpToDepth(child, maxDepth, currentDepth + 1);
		}
	}

	public Node execute(Node tree, String goal, int maxDepth) {
		return depthLimitedSearch(tree, goal, maxDepth);
	}
}
