import java.util.List;

// Stratégie de résolution qui applique uniquement la règle de déduction DR3
public class DR3OnlyStrategy implements SolvingStrategy {
    
    // Implémente la méthode solve pour appliquer seulement la règle DR3 sur la grille
    @Override
    public boolean solve(int[][] grid, List<DeductionRule> rules) {
        for (DeductionRule rule : rules) {
            if (rule instanceof DR3) { // Vérifie si la règle est une instance de DR3
                return rule.applyRule(grid); // Applique DR3 et retourne le résultat
            }
        }
        return false; // Retourne false si DR3 n'est pas trouvée dans les règles
    }
}
