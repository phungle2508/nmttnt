package lab5;


import java.util.List;

public class Test {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");

		System.out.println("Initial State:");
		System.out.println(p.getInitialState());
		
		System.out.println("Goal State:");
		System.out.println(p.getGoalState());

		// Test successors
		List<Node> children = p.getSuccessors(p.getInitialState());
		System.out.println("\nSuccessors of initial state (" + children.size() + "):");
		for (Node child : children) {
			System.out.println("H value: " + p.computeH2(child));
			System.out.println(child);
		}

		// Test all search algorithms
		System.out.println("\n" + "=".repeat(60));
		System.out.println("TESTING ALL SEARCH ALGORITHMS");
		System.out.println("=".repeat(60));
		
		// Test 1: Breadth-First Search
		System.out.println("\n=== 1. Testing Breadth-First Search (BFS) ===");
		testAlgorithm("BFS", new Task4_BFSDFSHillClimbingAlgo()::executeBFS, p);
		
		// Test 2: Depth-First Search
		System.out.println("\n=== 2. Testing Depth-First Search (DFS) ===");
		testAlgorithm("DFS", new Task4_BFSDFSHillClimbingAlgo()::executeDFS, p);
		
		// Test 3: Hill Climbing
		System.out.println("\n=== 3. Testing Hill Climbing ===");
		testAlgorithm("Hill Climbing", new Task4_BFSDFSHillClimbingAlgo()::executeHillClimbing, p);
		
		// Test 4: Greedy Best-First Search
		System.out.println("\n=== 4. Testing Greedy Best-First Search ===");
		testAlgorithm("Greedy", new GreedyBestFirstSearchAlgo()::execute, p);
		
		// Test 5: A* Search
		System.out.println("\n=== 5. Testing A* Search ===");
		testAlgorithm("A*", new AStarSearchAlgo()::execute, p);
		
		System.out.println("\n" + "=".repeat(60));
		System.out.println("ALGORITHM TESTING COMPLETE");
		System.out.println("=".repeat(60));
	}
	
	// Generic method to test any search algorithm
	private static void testAlgorithm(String algorithmName, java.util.function.Function<Puzzle, Node> algorithm, Puzzle p) {
		System.out.println("Initial H value: " + p.computeH2(p.getInitialState()));
		System.out.println("Goal H value: " + p.computeH2(p.getGoalState()));
		
		long startTime = System.currentTimeMillis();
		Node solution = algorithm.apply(p);
		long endTime = System.currentTimeMillis();
		
		if (solution != null) {
			System.out.println("✓ " + algorithmName + " SOLUTION FOUND");
			System.out.println("  Time taken: " + (endTime - startTime) + " ms");
			System.out.println("  Path length: " + solution.getG());
			System.out.println("  Final H value: " + p.computeH2(solution));
			System.out.println("  Goal reached: " + solution.equals(p.getGoalState()));
		} else {
			System.out.println("✗ " + algorithmName + " NO SOLUTION FOUND");
			System.out.println("  Time taken: " + (endTime - startTime) + " ms");
		}
	}
}
