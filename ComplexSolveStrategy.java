import java.util.List;

public class ComplexSolveStrategy implements SolvingStrategy {
    @Override
    public boolean solve(int[][] grid, List<DeductionRule> rules) {
        boolean changed;
        boolean overallChanged = false;

        do {
            changed = false;
            for (DeductionRule rule : rules) {
                if (rule.applyRule(grid)) {
                    changed = true;
                    overallChanged = true; // Indique qu'un changement global a eu lieu
                }
            }
        } while (changed); // Continue tant qu'il y a des changements

        return overallChanged; // Retourne true si au moins un changement a été fait
    }
}
