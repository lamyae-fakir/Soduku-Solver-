import java.util.List;

public class SimpleSolveStrategy implements SolvingStrategy {
    @Override
    public boolean solve(int[][] grid, List<DeductionRule> rules) {
        boolean changed = false;

        for (DeductionRule rule : rules) {
            if (rule instanceof DR1) {
                if (rule.applyRule(grid)) {
                    changed = true;
                }
            }
        }

        return changed; // Retourne true si un changement a été fait, sinon false
    }
}
