package com.chessonline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PawnTest {
    private ChessBoard chessBoard;
    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whitePawn = (Pawn) chessBoard.getPieceAt("a2");
        blackPawn = (Pawn) chessBoard.getPieceAt("b7");
    }

    @Test
    public void moveOneSquare() {
        assertTrue(chessBoard.movePiece(whitePawn.getPosition(), "a3"));
        assertEquals("a3", whitePawn.getPosition());

        // Test du mouvement pour un pion noir
        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), "b6"));
        assertEquals("b6", blackPawn.getPosition());
    }

    @Test
    public void moveTwoSquare() {
        // Test du mouvement pour un pion blanc
        assertTrue(chessBoard.movePiece(whitePawn.getPosition(), "a4"));
        assertEquals("a4", whitePawn.getPosition());

        // Test du mouvement pour un pion noir
        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), "b5"));
        assertEquals("b5", blackPawn.getPosition());
    }

    @Test
    public void moveBackwards() {
        // Test du mouvement impossible pour un pion blanc
        assertFalse(chessBoard.movePiece(whitePawn.getPosition(), "a1"));
        assertEquals("a2", whitePawn.getPosition());

        // Test du mouvement impossible pour un pion noir
        assertFalse(chessBoard.movePiece(blackPawn.getPosition(), "b8"));
        assertEquals("b7", blackPawn.getPosition());
    }

    @Test
    public void capturePawnDiagonally() {
        // Test de capture diagonale
        blackPawn = new Pawn("black", "b3");
        chessBoard.placePiece(blackPawn);

        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), whitePawn.getPosition()));
        assertEquals("a2", blackPawn.getPosition());
    }

    @Test
    public void capturePawnForward() {
        blackPawn = new Pawn("black", "a3");
        chessBoard.placePiece(blackPawn);

        assertFalse(chessBoard.movePiece(blackPawn.getPosition(), whitePawn.getPosition()));
        assertEquals("a3", blackPawn.getPosition());
    }
}