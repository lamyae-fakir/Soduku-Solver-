public abstract class DeductionRule {
    // Méthode abstraite que chaque règle de déduction va implémenter
    public abstract boolean applyRule(int[][] grid);
    // Vérifie si `value` peut être placée dans `grid[row][col]` sans causer de conflit
public boolean isValueValid(int[][] grid, int row, int col, int value) {
    // Vérifier les conflits dans la ligne
    for (int i = 0; i < 9; i++) {
        if (grid[row][i] == value) return false;
    }

    // Vérifier les conflits dans la colonne
    for (int i = 0; i < 9; i++) {
        if (grid[i][col] == value) return false;
    }

    // Vérifier les conflits dans la sous-grille 3x3
    int boxRow = (row / 3) * 3;
    int boxCol = (col / 3) * 3;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (grid[boxRow + i][boxCol + j] == value) return false;
        }
    }

    return true; // Retourne vrai si `value` peut être placée sans conflit
}

}
