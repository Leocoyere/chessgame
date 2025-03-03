package com.chessonline;

import java.util.ArrayList;
import java.util.List;

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

        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isCaptureMovementValid(String newPosition) {
        return isMovementValid(newPosition);
    }

    public List<String> getPossibleMoves() {
        List<String> moves = new ArrayList<>();
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        Position current = new Position(position);
        
        for (int[] dir : directions) {
            char newCol = (char) (current.col + dir[0]);
            int newRow = current.row + dir[1];

            if (isWithinBounds(newCol, newRow)) {
                moves.add("" + newCol + newRow);
            }
        }

        return moves;
    }

    private boolean isWithinBounds(char col, int row) {
        return col >= 'a' && col <= 'h' && row > 0 && row <= 8;
    }
}
