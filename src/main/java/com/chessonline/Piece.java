package com.chessonline;

public abstract class Piece {
    protected String name;
    protected String color;
    protected String position;

    public Piece(String name, String color, String position) {
        this.name = name;
        this.color = color;
        this.position = position;
    }

    public abstract boolean move(String newPosition);

    public abstract boolean isMovementValid(String newPosition);

    public abstract boolean isCaptureMovementValid(String newPosition);

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    protected static class Position {
        char col;
        int row;

        Position(String position) {
            this.col = position.charAt(0);
            this.row = position.charAt(1) - '0';
        }
    }
}
