import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;// Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();

    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {
        // Enter your code here

        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            // Create a new population
            List<Node> newPopulation = new ArrayList<Node>();

            // Fill the new population
            while (newPopulation.size() < POP_SIZE) {
                // Select parents
                Node parent1 = getParentByTournamentSelection();
                Node parent2 = getParentByTournamentSelection();

                // Reproduce
                Node child = reproduce(parent1, parent2);

                // Mutate
                if (rd.nextDouble() <= MUTATION_RATE) {
                    mutate(child);
                }

                // Add child to new population
                newPopulation.add(child);
            }

            // Replace old population with new population
            population = newPopulation;

            // Check for solution
            for (Node individual : population) {
                if (individual.getH() == 0) {
                    return individual; // Solution found
                }
            }
        }
        return null;
    }

    // Mutate an individual by selecting a random Queen and
    // move it to a random row.
    public void mutate(Node node) {
        // Enter your code here
        int index = rd.nextInt(Node.N);
        int row = rd.nextInt(Node.N);
        node.setRow(index, row);
    }

    // Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        // Enter your code here
        Node child = new Node();
        int crossoverPoint = rd.nextInt(Node.N);
        for (int i = 0; i < Node.N; i++) {
            if (i < crossoverPoint) {
                child.setRow(i, x.getRow(i));
            } else {
                child.setRow(i, y.getRow(i));
            }
        }
        return child;
    }

    // Select K individuals from the population at random and
    // select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        // Enter your code here
        int K = 10; // Tournament size
        List<Node> tournament = new ArrayList<Node>();
        for (int i = 0; i < K; i++) {
            int index = rd.nextInt(POP_SIZE);
            tournament.add(population.get(index));
        }
        tournament.sort(null);
        return tournament.get(0);
    }

    // Select a random parent from the population
    public Node getParentByRandomSelection() {
        // Enter your code here
        int index = rd.nextInt(POP_SIZE);
        return population.get(index);
    }
}