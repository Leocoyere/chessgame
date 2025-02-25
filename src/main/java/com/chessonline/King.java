package com.chessonline;

public class King extends Piece {

    public King(String color, String position) {
        super("king", color, position);
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

        int rowDiff = Math.abs(target.row - current.row);
        int colDiff = Math.abs(target.col - current.col);

        // Le roi ne peut se déplacer que d'une seule case dans toutes les directions
        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isCaptureMovementValid(String newPosition) {
        return isMovementValid(newPosition);
    }
}
