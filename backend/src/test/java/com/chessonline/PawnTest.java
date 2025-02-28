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
        whitePawn = new Pawn("white", "d4");
        blackPawn = new Pawn("black", "e5");
        chessBoard.placePiece(whitePawn);
        chessBoard.placePiece(blackPawn);
    }

    @Test
    public void moveOneSquare() {
        assertTrue(chessBoard.movePiece(whitePawn.getPosition(), "d5"));
        assertEquals("d5", whitePawn.getPosition());

        // Test du mouvement pour un pion noir
        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), "e4"));
        assertEquals("e4", blackPawn.getPosition());
    }

    @Test
    public void moveTwoSquare() {
        // Test du mouvement pour un pion blanc
        assertTrue(chessBoard.movePiece(whitePawn.getPosition(), "d6"));
        assertEquals("d6", whitePawn.getPosition());

        // Test du mouvement pour un pion noir
        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), "e3"));
        assertEquals("e3", blackPawn.getPosition());
    }

    @Test
    public void moveBackwards() {
        // Test du mouvement impossible pour un pion blanc
        assertFalse(chessBoard.movePiece(whitePawn.getPosition(), "d3"));
        assertEquals("d4", whitePawn.getPosition());

        // Test du mouvement impossible pour un pion noir
        assertFalse(chessBoard.movePiece(blackPawn.getPosition(), "e6"));
        assertEquals("e5", blackPawn.getPosition());
    }

    @Test
    public void capturePawnDiagonally() {
        // Test de capture diagonale
        assertTrue(chessBoard.movePiece(blackPawn.getPosition(), whitePawn.getPosition()));
        assertEquals("d4", blackPawn.getPosition());
    }

    @Test
    public void capturePawnForward() {
        blackPawn = new Pawn("black", "d5");
        chessBoard.placePiece(blackPawn);

        assertFalse(chessBoard.movePiece(blackPawn.getPosition(), whitePawn.getPosition()));
        assertEquals("d5", blackPawn.getPosition());
    }
}