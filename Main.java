import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            SudokuSolver solver = new SudokuSolver(scanner);
            solver.loadGrid("sudoku.txt");
            
            System.out.println("Résolution séquentielle en appliquant DR1, DR2 et DR3...");
            solver.solve();
            
            System.out.println("Grille après la résolution :");
            solver.displayGrid();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la grille : " + e.getMessage());
        } finally {
            scanner.close(); // Fermer le scanner uniquement à la fin de tout le programme
        }
    }
}
