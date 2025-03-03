package com.chessonline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightTest {
    private ChessBoard chessBoard;
    private Knight whiteKnight;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whiteKnight = new Knight("white", "d4");
        chessBoard.placePiece(whiteKnight);
    }

    @Test
    public void moveForwardLeft() {
        assertTrue(chessBoard.movePiece(whiteKnight.getPosition(), "b5"));
        assertEquals("b5", whiteKnight.getPosition());
    }

    @Test
    public void moveForwardRight() {
        assertTrue(chessBoard.movePiece(whiteKnight.getPosition(), "e6"));
        assertEquals("e6", whiteKnight.getPosition());
    }

    @Test
    public void moveBackwardRight() {
        assertTrue(chessBoard.movePiece(whiteKnight.getPosition(), "f3"));
        assertEquals("f3", whiteKnight.getPosition());
    }

    @Test
    public void moveBackwardLeft() {
        assertTrue(chessBoard.movePiece(whiteKnight.getPosition(), "c2"));
        assertEquals("c2", whiteKnight.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        chessBoard.placePiece(new Pawn("white", "e2"));
        assertFalse(chessBoard.movePiece(whiteKnight.getPosition(), "e2"));
        assertEquals("d4", whiteKnight.getPosition());
    }

    @Test
    public void captureEnemyPiece() {
        chessBoard.placePiece(new Pawn("black", "c6"));
        assertTrue(chessBoard.movePiece(whiteKnight.getPosition(), "c6"));
        assertEquals("c6", whiteKnight.getPosition());
    }
}