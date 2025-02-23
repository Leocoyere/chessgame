package com.chessonline;

public class Knight extends Piece {

    public Knight(String color, String position) {
        super("knight", color, position);
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

        int colDiff = Math.abs(target.col - current.col);
        int rowDiff = Math.abs(target.row - current.row);

        // Mouvement en "L" : 2 cases dans une direction, 1 case perpendiculaire
        return (colDiff == 2 && rowDiff == 1) || (colDiff == 1 && rowDiff == 2);
    }

    @Override
    public boolean isCaptureMovementValid(String newPosition) {
        return isMovementValid(newPosition);
    }
}
