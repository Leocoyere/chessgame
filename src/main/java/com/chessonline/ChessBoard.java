package com.chessonline;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        board = new Piece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {

        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            board[1][i] = new Pawn("black", column + "7");
        }

        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            board[6][i] = new Pawn("white", column + "2");
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            // System.out.print(i + 1);
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("[ ] ");
                } else {
                    // System.out.print("[" + board[i][j].getPosition() + "] ");
                    System.out.print("[" + board[i][j].getColor().substring(0, 1).toUpperCase() + "] ");
                }
            }
            System.out.println();
        }
    }

    public boolean movePiece(String fromPosition, String toPosition) {
        int[] fromCoords = positionToCoordinates(fromPosition);
        int[] toCoords = positionToCoordinates(toPosition);

        // Vérifier si un pion existe à la position "fromPosition"
        Piece piece = board[fromCoords[0]][fromCoords[1]];
        if (piece == null) {
            System.out.println("Aucun pion à cette position !");
            return false;
        }

        // Vérifier si la case est occupée par une pièce ennemie
        Piece targetPiece = board[toCoords[0]][toCoords[1]];
        if (targetPiece != null) {
            if (piece.getColor() == targetPiece.getColor()) return false;
            if (!piece.isCaptureValid(toPosition)) return false;

            board[toCoords[0]][toCoords[1]] = null;
        }
 
        if (!piece.move(toPosition)) return false;
        
        board[toCoords[0]][toCoords[1]] = piece;
        board[fromCoords[0]][fromCoords[1]] = null;
        return true;
    }

    private int[] positionToCoordinates(String position) {
        int row = 8 - Integer.parseInt(position.substring(1));  // Conversion de la ligne
        int col = position.charAt(0) - 'a';  // Conversion de la colonne
        return new int[] { row, col };
    }
}
