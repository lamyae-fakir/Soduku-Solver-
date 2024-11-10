import java.util.List;

public class DR1OnlyStrategy implements SolvingStrategy {
    @Override
    public boolean solve(int[][] grid, List<DeductionRule> rules) {
        for (DeductionRule rule : rules) {
            if (rule instanceof DR1) { // Appliquer uniquement DR1
                return rule.applyRule(grid);
            }
        }
        return false;
    }
}
