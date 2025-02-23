package com.chessonline;

public class Bishop extends Piece {

    private ChessBoard chessBoard;

    public Bishop(String color, String position, ChessBoard chessBoard) {
        super("bishop", color, position);
        this.chessBoard = chessBoard;
    }

    @Override
    public boolean move(String newPosition) {

        if (!isMovementValid(newPosition)) return false;

        setPosition(newPosition);

        return true;
    }

    @Override
    public boolean isMovementValid(String newPosition) {
        Position current = new Position(position);
        Position target = new Position(newPosition);

        // Vérifie que le mouvement est bien en diagonale
        if (Math.abs(current.col - target.col) != Math.abs(current.row - target.row)) {
            return false;
        }

        // Vérifie qu'il n'y a pas d'obstacle sur la diagonale
        if (!isDiagonalClear(current, target)) return false;

        return true;
    }

    private boolean isDiagonalClear(Position current, Position target) {
        int rowStep = (target.row > current.row) ? 1 : -1;
        int colStep = (target.col > current.col) ? 1 : -1;

        int row = current.row + rowStep;
        char col = (char) (current.col + colStep);

        while (row != target.row && col != target.col) {
            if (chessBoard.getPieceAt("" + col + row) != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    @Override
    public boolean isCaptureMovementValid(String newPosition) {
        return isMovementValid(newPosition);
    }
}
