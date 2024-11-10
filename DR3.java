import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class DR3 extends DeductionRule {
    @Override
    public boolean applyRule(int[][] grid) {
        boolean changed = false;

        // Vérifier les paires dans les lignes
        for (int row = 0; row < 9; row++) {
            if (findAndEliminateNakedPairs(grid, row, true)) {
                changed = true;
            }
        }

        // Vérifier les paires dans les colonnes
        for (int col = 0; col < 9; col++) {
            if (findAndEliminateNakedPairs(grid, col, false)) {
                changed = true;
            }
        }

        // Vérifier les paires dans chaque sous-grille 3x3
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                if (findAndEliminateNakedPairsInBox(grid, boxRow, boxCol)) {
                    changed = true;
                }
            }
        }

        return changed;
    }

    // Trouver et éliminer les paires nues dans une ligne ou une colonne
    // Trouver et éliminer les paires nues dans une ligne ou une colonne
private boolean findAndEliminateNakedPairs(int[][] grid, int index, boolean isRow) {
    boolean changed = false;
    List<int[]> pairs = new ArrayList<>();

    // Recherche des paires de candidats possibles
    for (int i = 0; i < 9; i++) {
        int row = isRow ? index : i;
        int col = isRow ? i : index;

        if (grid[row][col] == 0) {
            int[] candidates = findCandidates(grid, row, col);
            if (candidates.length == 2) {
                pairs.add(new int[]{row, col});
            }
        }
    }

    // Vérifier les paires nues
    for (int i = 0; i < pairs.size(); i++) {
        for (int j = i + 1; j < pairs.size(); j++) {
            int[] cell1 = pairs.get(i);
            int[] cell2 = pairs.get(j);

            int[] candidates1 = findCandidates(grid, cell1[0], cell1[1]);
            int[] candidates2 = findCandidates(grid, cell2[0], cell2[1]);

            if (candidates1.length == 2 && candidates2.length == 2 &&
                    candidates1[0] == candidates2[0] && candidates1[1] == candidates2[1]) {

                // Éliminer les candidats des autres cases de la ligne ou de la colonne
                for (int k = 0; k < 9; k++) {
                    int row = isRow ? index : k;
                    int col = isRow ? k : index;

                    if ((row != cell1[0] || col != cell1[1]) && (row != cell2[0] || col != cell2[1])) {
                        if (grid[row][col] == 0 && isValueValid(grid, row, col, candidates1[0])) {
                            if (removeCandidates(grid, row, col, candidates1)) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
    }
    return changed;
}

// Trouver et éliminer les paires nues dans une sous-grille 3x3
private boolean findAndEliminateNakedPairsInBox(int[][] grid, int boxRow, int boxCol) {
    boolean changed = false;
    List<int[]> pairs = new ArrayList<>();

    int startRow = boxRow * 3;
    int startCol = boxCol * 3;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            int row = startRow + i;
            int col = startCol + j;

            if (grid[row][col] == 0) {
                int[] candidates = findCandidates(grid, row, col);
                if (candidates.length == 2) {
                    pairs.add(new int[]{row, col});
                }
            }
        }
    }

    // Vérifier les paires nues dans le bloc
    for (int i = 0; i < pairs.size(); i++) {
        for (int j = i + 1; j < pairs.size(); j++) {
            int[] cell1 = pairs.get(i);
            int[] cell2 = pairs.get(j);

            int[] candidates1 = findCandidates(grid, cell1[0], cell1[1]);
            int[] candidates2 = findCandidates(grid, cell2[0], cell2[1]);

            if (candidates1.length == 2 && candidates2.length == 2 &&
                    candidates1[0] == candidates2[0] && candidates1[1] == candidates2[1]) {

                // Éliminer les candidats des autres cases du bloc
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        int row = startRow + m;
                        int col = startCol + n;

                        if ((row != cell1[0] || col != cell1[1]) && (row != cell2[0] || col != cell2[1])) {
                            if (grid[row][col] == 0 && isValueValid(grid, row, col, candidates1[0])) {
                                if (removeCandidates(grid, row, col, candidates1)) {
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return changed;
}


    // Fonction pour trouver les candidats possibles pour une case donnée
    private int[] findCandidates(int[][] grid, int row, int col) {
        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);

        // Exclure les candidats déjà présents dans la ligne, colonne et sous-grille
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] != 0) possible[grid[row][i]] = false;
            if (grid[i][col] != 0) possible[grid[i][col]] = false;
        }

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] != 0) {
                    possible[grid[boxRow + i][boxCol + j]] = false;
                }
            }
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (possible[i]) {
                candidates.add(i);
            }
        }
        return candidates.stream().mapToInt(i -> i).toArray();
    }

    // Fonction pour supprimer les candidats d'une case
    private boolean removeCandidates(int[][] grid, int row, int col, int[] candidates) {
        // Pour cet exemple, on suppose qu'on a une logique pour enlever les candidats (en général avec des structures avancées)
        return false;
    }
}
