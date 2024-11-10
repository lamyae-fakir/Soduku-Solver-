# Soduku-Solver-
#Introduction
Ce projet a pour objectif de développer un solveur de Sudoku en Java qui applique un ensemble de règles de déduction (DR1 , DR2 , DR3 ) pour résoudre une grille de Sudoku. 
Si les règles de déduction ne suffisent pas pour terminer la grille, le solveur invite l'utilisateur à remplir une case. 
Ce projet a été réalisé dans le cadre d'un travail universitaire.

##Prérequis
Java : Ce projet nécessite Java pour compiler et exécuter le programme. Assurez-vous d'avoir une version récente installée sur votre machine.
Fichier d'entrée : Le programme utilise un fichier texte (par défaut sudoku.txt) contenant la grille de Sudoku. La grille doit être dans un format spécifique .


##Format du fichier d'entrée
Le fichier d'entrée sudoku.txt doit contenir la grille de Sudoku sous la forme d'une matrice de 9x9 avec des chiffres séparés par des virgules. Les valeurs 0 représentent les cases vides.

##Exemple de fichier soduku.txt
5,3,0,0,7,0,0,0,0
6,0,0,1,9,5,0,0,0
0,9,8,0,0,0,0,6,0
8,0,0,0,6,0,0,0,3
4,0,0,8,0,3,0,0,1
7,0,0,0,2,0,0,0,6
0,6,0,0,0,0,2,8,0
0,0,0,4,1,9,0,0,5
0,0,0,0,8,0,0,7,9


##Instructions d'utilisation
###Cloner le dépôt GitHub :

git clone [<lien_du_dépôt_github>](https://github.com/lamyae-fakir/Soduku-Solver-.git)
cd sudoku-solver

###Compiler le projet :
javac *.java


###Exécuter le programme :
java Main

Le programme charge le fichier sudoku.txt, applique les règles de déduction, et affiche la grille une fois résolue. Si les règles ne suffisent pas, il demandera à l'utilisateur de remplir manuellement les cases restantes.


##Règles de déduction
Le solveur applique les règles suivantes pour déduire les valeurs manquantes :

DR1 : Remplit une case si elle ne peut contenir qu'une seule valeur possible.
DR2 : Remplit une valeur dans une ligne, colonne, ou sous-grille si une seule position est possible pour cette valeur.
DR3 : Utilise la méthode des "paires nues" pour éliminer des candidats dans une ligne, colonne, ou sous-grille.
Ces règles sont appliquées séquentiellement jusqu'à ce qu'aucune nouvelle déduction ne puisse être faite. Si la grille reste incomplète, l'utilisateur est invité à entrer une valeur manuellement.

###Stratégies de résolution
Le programme utilise différentes stratégies pour appliquer les règles de déduction :

SimpleSolveStrategy : Applique uniquement la règle DR1.
ComplexSolveStrategy : Applique les règles DR1, DR2 et DR3 de manière itérative jusqu'à ce qu'aucun changement supplémentaire ne soit détecté.
