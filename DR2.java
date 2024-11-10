public class DR2 extends DeductionRule {
    // Applique la règle DR2 en essayant de placer chaque nombre de 1 à 9
    @Override
    public boolean applyRule(int[][] grid) {
        boolean changed = false; // Indique si la grille a été modifiée

        // Boucle pour chaque nombre de 1 à 9
        for (int num = 1; num <= 9; num++) {
            // Tente de placer le nombre dans chaque ligne
            for (int row = 0; row < 9; row++) {
                if (placeInRow(grid, row, num)) {
                    changed = true; // La grille est modifiée si le nombre est placé
                }
            }
            // Tente de placer le nombre dans chaque colonne
            for (int col = 0; col < 9; col++) {
                if (placeInColumn(grid, col, num)) {
                    changed = true;
                }
            }
            // Tente de placer le nombre dans chaque sous-grille 3x3
            for (int box = 0; box < 9; box++) {
                if (placeInBox(grid, box, num)) {
                    changed = true;
                }
            }
        }
        return changed; // Retourne si la grille a été modifiée
    }

    // Tente de placer `num` dans une ligne si une seule position est possible
    private boolean placeInRow(int[][] grid, int row, int num) {
        int count = 0, colPos = -1; // Compteur et position possible
        for (int col = 0; col < 9; col++) {
            // Vérifie si la case est vide et peut contenir `num`
            if (grid[row][col] == 0 && canPlace(grid, row, col, num)) {
                count++; // Incrémente le nombre de positions possibles
                colPos = col; // Enregistre la position possible
            }
        }
        // Si une seule position est possible, utilise `isValueValid` avant de placer `num`
        if (count == 1 && isValueValid(grid, row, colPos, num)) {
            grid[row][colPos] = num;
            return true; // Indique qu'un placement a été effectué
        }
        return false;
    }
    

    // Tente de placer `num` dans une colonne si une seule position est possible
    private boolean placeInColumn(int[][] grid, int col, int num) {
        int count = 0, rowPos = -1;
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == 0 && canPlace(grid, row, col, num)) {
                count++;
                rowPos = row;
            }
        }
        // Si une seule position est possible, utilise `isValueValid` avant de placer `num`
        if (count == 1 && isValueValid(grid, rowPos, col, num)) {
            grid[rowPos][col] = num;
            return true;
        }
        return false;
    }
    

    // Tente de placer `num` dans une sous-grille 3x3 si une seule position est possible
    private boolean placeInBox(int[][] grid, int box, int num) {
        int count = 0, rowPos = -1, colPos = -1;
        int startRow = (box / 3) * 3; // Début de la sous-grille en ligne
        int startCol = (box % 3) * 3; // Début de la sous-grille en colonne
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = startRow + i;
                int col = startCol + j;
                // Vérifie si la case est vide et peut contenir `num`
                if (grid[row][col] == 0 && canPlace(grid, row, col, num)) {
                    count++;
                    rowPos = row;
                    colPos = col;
                }
            }
        }
        // Si une seule position est possible, utilise `isValueValid` avant de placer `num`
        if (count == 1 && isValueValid(grid, rowPos, colPos, num)) {
            grid[rowPos][colPos] = num;
            return true;
        }
        return false;
    }
    

    // Vérifie si `num` peut être placé dans une case spécifique sans conflits
    private boolean canPlace(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            // Vérifie les conflits dans la ligne et la colonne
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Vérifie les conflits dans la sous-grille 3x3
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true; // Retourne vrai si aucun conflit n'a été trouvé
    }
}
