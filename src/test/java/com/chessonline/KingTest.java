package com.chessonline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingTest {
    private ChessBoard chessBoard;
    private King whiteKing;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whiteKing = new King("white", "d4");
        chessBoard.placePiece(whiteKing);
    }

    @Test
    public void moveOneSquareDiagonally() {
        assertTrue(chessBoard.movePiece(whiteKing.getPosition(), "e5"));
        assertEquals("e5", whiteKing.getPosition());
    }

    @Test
    public void moveOneSquareHorizontally() {
        assertTrue(chessBoard.movePiece(whiteKing.getPosition(), "c4"));
        assertEquals("c4", whiteKing.getPosition());
    }

    @Test
    public void moveOneSquareVertically() {
        assertTrue(chessBoard.movePiece(whiteKing.getPosition(), "d5"));
        assertEquals("d5", whiteKing.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        chessBoard.placePiece(new Pawn("white", "d5"));
        assertFalse(chessBoard.movePiece(whiteKing.getPosition(), "d5"));
        assertEquals("d4", whiteKing.getPosition());
    }

    @Test
    public void captureEnemyPieceDiagonally() {
        chessBoard.placePiece(new Pawn("black", "c3"));
        assertTrue(chessBoard.movePiece(whiteKing.getPosition(), "c3"));
        assertEquals("c3", whiteKing.getPosition());
    }

    @Test
    public void captureEnemyPiece() {
        chessBoard.placePiece(new Pawn("black", "d5"));
        assertTrue(chessBoard.movePiece(whiteKing.getPosition(), "d5"));
        assertEquals("d5", whiteKing.getPosition());
    }
}
