package com.chessonline;

public class Queen extends Piece {
    private ChessBoard chessBoard;

    public Queen(String color, String position, ChessBoard chessBoard) {
        super("queen", color, position);
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

        // Vérifie si le mouvement est en ligne droite (comme la Tour)
        boolean isStraightMove = (current.col == target.col || current.row == target.row);
        
        // Vérifie si le mouvement est en diagonale (comme le Fou)
        boolean isDiagonalMove = Math.abs(current.col - target.col) == Math.abs(current.row - target.row);

        if (!isStraightMove && !isDiagonalMove) {
            return false;
        }

        // Vérifie si le chemin est dégagé
        return isClearPath(current, target);
    }

    private boolean isClearPath(Position current, Position target) {
        if (current.col == target.col) {
            return isFileClear(current, target); // Mouvement vertical (Tour)
        } else if (current.row == target.row) {
            return isRankClear(current, target); // Mouvement horizontal (Tour)
        } else {
            return isDiagonalClear(current, target); // Mouvement diagonal (Fou)
        }
    }

    private boolean isFileClear(Position current, Position target) {
        int step = (target.row > current.row) ? 1 : -1;
        for (int row = current.row + step; row != target.row; row += step) {
            if (chessBoard.getPieceAt("" + current.col + row) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isRankClear(Position current, Position target) {
        int step = (target.col > current.col) ? 1 : -1;
        for (char col = (char) (current.col + step); col != target.col; col += step) {
            if (chessBoard.getPieceAt("" + col + current.row) != null) {
                return false;
            }
        }
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
