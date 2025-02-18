package com.chessonline;

public class App {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        // Afficher l'état initial du plateau
        System.out.println("Plateau initial :");
        chessBoard.displayBoard();

        // Déplacer un pion (par exemple, pion blanc de a2 à a3)
        chessBoard.movePiece("a2", "a3");

        // Afficher l'état du plateau après le mouvement
        System.out.println("\nPlateau après le mouvement :");
        chessBoard.displayBoard();
    }
}