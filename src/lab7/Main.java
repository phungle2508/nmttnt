public class Main {
    public static void main(String[] args) {
        GA_NQueenAlgo ga = new GA_NQueenAlgo();
        ga.initPopulation();
        Node solution = ga.execute();
        if (solution != null) {
            solution.displayBoard();
        } else {
            System.out.println("No solution found.");
        }
    }
}