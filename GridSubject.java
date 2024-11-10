// Classe GridSubject qui gère les observateurs de la grille de Sudoku
import java.util.List;
import java.util.ArrayList;

public class GridSubject {
    // Liste des observateurs enregistrés
    private List<GridObserver> observers = new ArrayList<>();

    // Méthode pour ajouter un observateur à la liste
    public void addObserver(GridObserver observer) {
        observers.add(observer);
    }

    // Méthode pour supprimer un observateur de la liste
    public void removeObserver(GridObserver observer) {
        observers.remove(observer);
    }

    // Méthode pour notifier tous les observateurs d'un changement dans la grille
    // row, col : coordonnées de la case modifiée
    // value : nouvelle valeur de la case
    public void notifyObservers(int row, int col, int value) {
        for (GridObserver observer : observers) {
            observer.update(row, col, value); // Appelle la méthode update de chaque observateur
        }
    }
}
