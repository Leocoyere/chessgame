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
        whiteRook = new Rook("white", "a4", chessBoard);
        chessBoard.placePiece(whiteRook);
    }

    @Test
    public void moveHorizontally() {
        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "h4"));
        assertEquals("h4", whiteRook.getPosition());
    }

    @Test
    public void moveVertically() {
        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "a6"));
        assertEquals("a6", whiteRook.getPosition());
    }

    @Test
    public void moveDiagonally() {
        assertFalse(chessBoard.movePiece(whiteRook.getPosition(), "h6"));
        assertEquals("a4", whiteRook.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        // Place un pion qui bloque le chemin de la tour
        Pawn blockingPawn = new Pawn("white", "a2");
        chessBoard.placePiece(blockingPawn);

        assertFalse(chessBoard.movePiece(whiteRook.getPosition(), "a1"));
        assertEquals("a4", whiteRook.getPosition());
    }

    @Test
    public void capturePiece() {
        // Place une pièce ennemie pour capturer
        Pawn blackPawn = new Pawn("black", "a7");
        chessBoard.placePiece(blackPawn);

        assertTrue(chessBoard.movePiece(whiteRook.getPosition(), "a7"));
        assertEquals("a7", whiteRook.getPosition());
        chessBoard.displayBoard();
    }

    @Test
    public void invalidMove() {
        // La tour ne peut pas se déplacer en L comme un cavalier
        assertFalse(chessBoard.movePiece(whiteRook.getPosition(), "b6"));
        assertEquals("a4", whiteRook.getPosition());
    }
}
