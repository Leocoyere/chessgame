package com.chessonline;
import java.util.Scanner;

public class GameLoop {
    private ChessBoard chessBoard;
    private Scanner scanner;
    private boolean isWhiteTurn;

    public GameLoop() {
        chessBoard = new ChessBoard();
        scanner = new Scanner(System.in);
        isWhiteTurn = true;
        chessBoard.initializeBoard();
        chessBoard.removePiece(chessBoard.getPieceAt("b7"));
        chessBoard.placePiece(new Pawn("white", "b7"));
    }

    public void start() {
        System.out.println("Bienvenue dans Chess Online !");
        chessBoard.displayBoard();

        while (true) {
            String currentPlayer = isWhiteTurn ? "Blancs" : "Noirs";
            System.out.println(currentPlayer + ", entrez votre coup (ex: e2 e4) ou 'quit' pour quitter : ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("Fin de la partie.");
                break;
            }

            String[] move = input.split(" ");
            if (move.length != 2) {
                System.out.println("Format invalide. Utilisez : e2 e4");
                continue;
            }

            String from = move[0];
            String to = move[1];

            Piece piece = chessBoard.getPieceAt(from);
            if (piece == null || !piece.getColor().equals(isWhiteTurn ? "white" : "black")) {
                System.out.println("Ce n'est pas votre pièce !");
                continue;
            }

            if (!chessBoard.movePiece(from, to)) {
                System.out.println("Coup invalide, essayez encore.");
                continue;
            }

            chessBoard.displayBoard();

            if (chessBoard.isCheckmate(isWhiteTurn ? chessBoard.getBlackKing() : chessBoard.getWhiteKing())) {
                System.out.println("Échec et mat ! " + currentPlayer + " gagnent !");
                break;
            }

            isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();
    }
}
