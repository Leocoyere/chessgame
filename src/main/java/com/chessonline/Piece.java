package com.chessonline;

public abstract class Piece {
    protected String color;
    protected String position;

    public Piece(String color, String position) {
        this.color = color;
        this.position = position;
    }

    public abstract boolean move(String newPosition);

    public abstract boolean capture(String newPosition);

    public abstract boolean isMovementValid(String newPosition);

    public abstract boolean isCaptureValid(String newPosition);

    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
