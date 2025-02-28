package com.chessonline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenTest {
    private ChessBoard chessBoard;
    private Queen whiteQueen;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whiteQueen = new Queen("white", "a1", chessBoard);
        chessBoard.placePiece(whiteQueen);
    }

    @Test
    public void moveDiagonally() {
        assertTrue(chessBoard.movePiece(whiteQueen.getPosition(), "h8"));
        assertEquals("h8", whiteQueen.getPosition());
    }

    @Test
    public void moveHorizontally() {
        assertTrue(chessBoard.movePiece(whiteQueen.getPosition(), "h1"));
        assertEquals("h1", whiteQueen.getPosition());
    }

    @Test
    public void moveVertically() {
        assertTrue(chessBoard.movePiece(whiteQueen.getPosition(), "a8"));
        assertEquals("a8", whiteQueen.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        chessBoard.placePiece(new Pawn("white", "b2"));
        assertFalse(chessBoard.movePiece(whiteQueen.getPosition(), "h8"));
        assertEquals("a1", whiteQueen.getPosition());
    }

    @Test
    public void captureEnemyPieceDiagonally() {
        chessBoard.placePiece(new Pawn("black", "g7"));
        assertTrue(chessBoard.movePiece(whiteQueen.getPosition(), "g7"));
        assertEquals("g7", whiteQueen.getPosition());
    }

    @Test
    public void captureEnemyPiece() {
        chessBoard.placePiece(new Pawn("black", "a7"));
        assertTrue(chessBoard.movePiece(whiteQueen.getPosition(), "a7"));
        assertEquals("a7", whiteQueen.getPosition());
    }
}
