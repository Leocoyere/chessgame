package com.chessonline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class RookTest {
    private ChessBoard chessBoard;
    private Rook whiteRook;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whiteRook = new Rook("white", "a1", chessBoard);
        chessBoard.placePiece(whiteRook);
    }

    @Test
    public void moveHorizontally() {
        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "h1"));
        assertEquals("h1", whiteRook.getPosition());
    }

    @Test
    public void moveVertically() {
        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "a8"));
        assertEquals("a8", whiteRook.getPosition());
    }

    @Test
    public void moveDiagonally() {
        assertFalse(chessBoard.movePiece(whiteRook.getPosition(), "h6"));
        assertEquals("a1", whiteRook.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        // Place un pion qui bloque le chemin de la tour
        Pawn blockingPawn = new Pawn("white", "a2");
        chessBoard.placePiece(blockingPawn);

        assertFalse(chessBoard.movePiece(whiteRook.getPosition(), "a8"));
        assertEquals("a1", whiteRook.getPosition());
    }

    @Test
    public void captureEnemyPiece() {
        // Place une pièce ennemie pour capturer
        Pawn blackPawn = new Pawn("black", "a7");
        chessBoard.placePiece(blackPawn);

        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "a7"));
        assertEquals("a7", whiteRook.getPosition());
    }
}
