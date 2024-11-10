import java.util.List;
// Interface SolvingStrategy définissant une stratégie de résolution pour le Sudoku

public interface SolvingStrategy {
    boolean solve(int[][] grid, List<DeductionRule> rules);
}
