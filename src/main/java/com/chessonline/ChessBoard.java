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
            board[1][i] = new Pawn("white", column + "7");
        }

        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            board[6][i] = new Pawn("black", column + "2");
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print("[" + board[i][j].getPosition() + "] ");
                    // System.out.print("[" + board[i][j].getColor().substring(0, 1).toUpperCase() + "] ");
                }
            }
            System.out.println();
        }
    }

    public boolean movePiece(String fromPosition, String toPosition) {

        int fromRow = 8 - Integer.parseInt(fromPosition.substring(1));
        int fromCol = fromPosition.charAt(0) - 'a';

        int toRow = 8 - Integer.parseInt(toPosition.substring(1));
        int toCol = toPosition.charAt(0) - 'a';

        Piece pieceToMove = board[fromRow][fromCol];

        if (pieceToMove != null && pieceToMove.move(toPosition)) {
            board[toRow][toCol] = pieceToMove;
            board[fromRow][fromCol] = null;
            return true;
        }
        return false;
    }
}
