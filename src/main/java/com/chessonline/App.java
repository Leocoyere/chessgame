package com.chessonline;

public class App {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initializeBoard();

        System.out.println("Plateau initial :");
        chessBoard.displayBoard();

        System.out.println("\nPlateau après le mouvement :");
        chessBoard.displayBoard();
    }
}