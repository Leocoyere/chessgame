package com.chessonline;

public class App {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        // Afficher l'état initial du plateau
        System.out.println("Plateau initial :");
        chessBoard.displayBoard();

        // Afficher l'état du plateau après le mouvement
        System.out.println("\nPlateau après le mouvement :");
        chessBoard.displayBoard();
    }
}