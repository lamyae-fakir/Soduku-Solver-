// Interface GridObserver permettant de réagir aux changements dans une grille de Sudoku

public interface GridObserver {
    void update(int row, int col, int value);
}
