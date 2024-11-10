import java.util.Arrays;

// Classe DR1, qui hérite de DeductionRule et implémente une règle de déduction
public class DR1 extends DeductionRule {

    // Méthode principale qui applique la règle DR1 sur la grille Sudoku
    @Override
    public boolean applyRule(int[][] grid) {
        boolean changed = false; // Indicateur pour suivre si des changements ont été faits

        // Parcours de chaque case de la grille
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Vérifie si la case est vide (valeur 0)
                if (grid[row][col] == 0) {
                    // Cherche un candidat unique pour cette case
                    int possibleValue = findSingleCandidate(grid, row, col);
                    // Si un candidat unique est trouvé, on remplit la case
                    if (possibleValue != -1 && isValueValid(grid, row, col, possibleValue)) {
                        grid[row][col] = possibleValue;
                        changed = true;
                    }
                    
                }
            }
        }
        return changed; // Retourne vrai si des changements ont été faits, faux sinon

    }

    // Méthode pour trouver un candidat unique pour une case vide donnée
    private int findSingleCandidate(int[][] grid, int row, int col) {
        // Tableau pour suivre les candidats possibles de 1 à 9
        boolean[] possible = new boolean[10]; 
        Arrays.fill(possible, true); // Initialise toutes les valeurs possibles à "true"

        // Exclusion des valeurs déjà présentes dans la ligne
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] != 0) {
                possible[grid[row][i]] = false; // Marque le nombre comme impossible
            }
        }

        // Exclusion des valeurs déjà présentes dans la colonne
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] != 0) {
                possible[grid[i][col]] = false; // Marque le nombre comme impossible
            }
        }

        // Exclusion des valeurs déjà présentes dans la sous-grille 3x3
        int boxRow = (row / 3) * 3; // Ligne de départ de la sous-grille
        int boxCol = (col / 3) * 3; // Colonne de départ de la sous-grille
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] != 0) {
                    possible[grid[boxRow + i][boxCol + j]] = false; // Marque le nombre comme impossible
                }
            }
        }

        // Recherche de l'unique candidat dans les possibilités restantes
        int candidate = -1; // Variable pour stocker le candidat unique s'il existe
        for (int i = 1; i <= 9; i++) {
            if (possible[i]) { // Si le nombre est encore possible
                if (candidate == -1) { // Si aucun autre candidat n'a été trouvé
                    candidate = i; // On enregistre ce nombre comme candidat
                } else {
                    return -1; // Plus d'un candidat possible, retourne -1
                }
            }
        }
        return candidate; // Retourne le candidat unique ou -1 si aucun ou plusieurs candidats possibles
    }
}
