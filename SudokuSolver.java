import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principale qui représente le solveur de Sudoku
public class SudokuSolver {
    private int[][] grid; // La grille de Sudoku (9x9) à résoudre
    private List<DeductionRule> rules; // Liste des règles de déduction utilisées pour résoudre le Sudoku
    private List<GridObserver> observers; // Liste des observateurs qui sont notifiés des changements dans la grille
    private Scanner scanner; // Scanner pour gérer l'entrée utilisateur

    // Constructeur de la classe qui initialise la grille, les règles et les observateurs
    public SudokuSolver(Scanner scanner) {
        this.grid = new int[9][9]; // Initialisation de la grille vide
        this.scanner = scanner; // Scanner pour les entrées utilisateur

        // Initialisation des règles de déduction via une fabrique
        rules = DeductionRuleFactory.createRules();

        // Ajout des règles qui implémentent l'interface GridObserver dans la liste des observateurs
        observers = new ArrayList<>();
        for (DeductionRule rule : rules) {
            if (rule instanceof GridObserver) {
                observers.add((GridObserver) rule);
            }
        }
    }

    // Méthode pour charger la grille de Sudoku depuis un fichier
    public void loadGrid(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i < 9; i++) {
            String[] line = reader.readLine().split(",");
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }
        reader.close();
    }

    // Méthode pour résoudre le Sudoku en appliquant chaque règle de manière séquentielle
    public void solve() {
        while (true) {
            System.out.println("Application de DR1...");
            if (applyRule(new DR1()) && isSolved()) { // Appliquer DR1 et vérifier si la grille est résolue
                System.out.println("Sudoku résolu avec DR1 !");
                displayGrid();
                break;
            }

            System.out.println("Application de DR2...");
            if (applyRule(new DR2()) && isSolved()) { // Appliquer DR2 et vérifier si la grille est résolue
                System.out.println("Sudoku résolu avec DR2 !");
                displayGrid();
                break;
            }

            System.out.println("Application de DR3...");
            if (applyRule(new DR3()) && isSolved()) { // Appliquer DR3 et vérifier si la grille est résolue
                System.out.println("Sudoku résolu avec DR3 !");
                displayGrid();
                break;
            }

            // Si aucune des règles n'a pu résoudre la grille, demander une entrée utilisateur
            System.out.println("Aucune des règles ne permet de résoudre le Sudoku. Demandez à l'utilisateur d'entrer une valeur.");
            requestUserInput();

            // Vérifier si la grille est résolue après l'entrée utilisateur
            if (isSolved()) {
                System.out.println("Sudoku résolu après l'entrée utilisateur !");
                displayGrid();
                break;
            }
        }
    }

    // Applique une règle de déduction sur la grille et retourne true si un changement est effectué
    private boolean applyRule(DeductionRule rule) {
        return rule.applyRule(grid);
    }

    // Vérifie si la grille est complètement résolue (pas de cases vides)
    private boolean isSolved() {
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 0) { // Une case vide indique que la grille n'est pas encore résolue
                    return false;
                }
            }
        }
        return true;
    }

    // Vérifie si la grille contient des incohérences (ex: valeurs en double)
    private boolean hasInconsistency() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] != 0 && !isValueValid(row, col, grid[row][col])) {
                    return true; // Incohérence détectée si une valeur n'est pas valide
                }
            }
        }
        return false;
    }

    // Vérifie si une valeur dans une cellule respecte les règles du Sudoku
    private boolean isValueValid(int row, int col, int value) {
        // Vérification de la ligne et de la colonne
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value && i != col) return false; // Dupliqué dans la ligne
            if (grid[i][col] == value && i != row) return false; // Dupliqué dans la colonne
        }

        // Vérification dans la sous-grille 3x3
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == value && (boxRow + i != row || boxCol + j != col)) {
                    return false; // Dupliqué dans la sous-grille
                }
            }
        }
        return true;
    }

    // Méthode pour demander à l'utilisateur d'entrer une valeur dans une case
    private void requestUserInput() {
        displayGrid();
        System.out.println("La grille n'est pas encore complète. Veuillez entrer une valeur.");

        int row, col, value;

        // Demander l'indice de la ligne (1-9) et vérifier la validité de l'entrée
        do {
            System.out.print("Entrez la ligne (1-9) : ");
            row = scanner.nextInt() - 1;
        } while (row < 0 || row >= 9);

        // Demander l'indice de la colonne (1-9) et vérifier la validité de l'entrée
        do {
            System.out.print("Entrez la colonne (1-9) : ");
            col = scanner.nextInt() - 1;
        } while (col < 0 || col >= 9);

        // Vérifier si la case est vide, sinon informer l'utilisateur
        if (grid[row][col] != 0) {
            System.out.println("Cette case est déjà remplie. Veuillez choisir une case vide.");
            return;
        }

        // Demander la valeur (1-9) et vérifier sa validité
        do {
            System.out.print("Entrez la valeur (1-9) : ");
            value = scanner.nextInt();
        } while (value < 1 || value > 9);

        // Mettre à jour la grille avec la nouvelle valeur
        setValue(row, col, value);

        // Vérifier les incohérences après l'entrée utilisateur
        if (hasInconsistency()) {
            System.out.println("Incohérence détectée. please restart the solving");
        }
    }

    // Met à jour une case dans la grille et notifie les observateurs
    public void setValue(int row, int col, int value) {
        grid[row][col] = value; // Mise à jour de la valeur dans la grille
        notifyObservers(row, col, value); // Notifie les observateurs du changement
    }

    // Notifie tous les observateurs d'un changement dans une case spécifique
    private void notifyObservers(int row, int col, int value) {
        for (GridObserver observer : observers) {
            observer.update(row, col, value);
        }
    }

    // Affiche la grille de Sudoku dans la console
    public void displayGrid() {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
