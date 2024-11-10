import java.util.List;

public class DR2OnlyStrategy implements SolvingStrategy {
    @Override
    public boolean solve(int[][] grid, List<DeductionRule> rules) {
        for (DeductionRule rule : rules) {
            if (rule instanceof DR2) { // Appliquer uniquement DR2
                return rule.applyRule(grid);
            }
        }
        return false;
    }
}
