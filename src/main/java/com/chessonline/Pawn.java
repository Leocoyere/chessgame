package com.chessonline;

public class Pawn extends Piece {
    private int moveCount;

    public Pawn(String color, String position) {
        super(color, position);
        this.moveCount = 0;
    }

    @Override
    public boolean move(String newPosition) {

        if (!isMovementValid(newPosition)) return false;

        setPosition(newPosition);
        this.moveCount++;

        return true;
    }

    @Override
    public boolean capture(String newPosition) {

        if (!isCaptureValid(newPosition)) return false;
        
        setPosition(newPosition);
        this.moveCount++;

        return true;
    }

    @Override
    public boolean isMovementValid(String newPosition) {
        int currentRow = position.charAt(1) - '0';
        int newRow = newPosition.charAt(1) - '0';

        char currentCol = position.charAt(0);
        char newCol = newPosition.charAt(0);
        
        int direction = (color.equals("white")) ? 1 : -1;

        // Déplacement d'une case en avant
        if (newCol == currentCol && newRow == currentRow + direction) return true;
        // Déplacement de deux cases en avant pour le premier mouvement
        if (newCol == currentCol && newRow == currentRow + 2 * direction && this.moveCount == 0) return true;

        return false;
    }

    @Override
    public boolean isCaptureValid(String newPosition) {
        int currentRow = position.charAt(1) - '0';
        int newRow = newPosition.charAt(1) - '0';
        char currentCol = position.charAt(0);
        char newCol = newPosition.charAt(0);

        int direction = (color.equals("white")) ? 1 : -1;

        // Capture en diagonale (déplacement de 1 case en diagonale)
        if (Math.abs(newCol - currentCol) == 1 && newRow == currentRow + direction) return true;

        return false;
    }
}
