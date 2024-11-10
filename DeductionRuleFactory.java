import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Fabrique pour gérer et créer des règles de déduction pour le solveur de Sudoku
public class DeductionRuleFactory {

    private static final Map<String, DeductionRule> registeredRules = new HashMap<>();

    // Bloc statique pour initialiser la fabrique avec les règles de base (DR1, DR2, DR3)
    static {
        registeredRules.put("DR1", new DR1()); // Enregistrement de DR1
        registeredRules.put("DR2", new DR2()); // Enregistrement de DR2
        registeredRules.put("DR3", new DR3()); // Enregistrement de DR3
    }

    // Méthode pour ajouter une nouvelle règle à la fabrique
    public static void registerRule(String name, DeductionRule rule) {
        registeredRules.put(name, rule); // Ajout de la règle dans la Map
    }

    // Méthode pour créer une liste de toutes les règles enregistrées
    public static List<DeductionRule> createRules() {
        return new ArrayList<>(registeredRules.values()); // Retourne toutes les règles sous forme de liste
    }
}
