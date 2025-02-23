package com.chessonline;

public class Rook extends Piece {
    private ChessBoard chessBoard;

    public Rook(String color, String position, ChessBoard chessBoard) {
        super("rook", color, position);
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

        if (current.col != target.col && current.row != target.row) {
            return false;
        }

        if (!isClearPath(current, target)) return false;

        return true;
    }

    private boolean isClearPath(Position current, Position target) {
        if (current.col == target.col) {
            return isFileClear(current, target);
        } else {
            return isRankClear(current, target);
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

    @Override
    public boolean isCaptureMovementValid(String newPosition) {
        return isMovementValid(newPosition);
    }
}
