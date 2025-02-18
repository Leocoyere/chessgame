package com.chessonline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PawnTest {
    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void init() {
        whitePawn = new Pawn("white", "e4");
        blackPawn = new Pawn("black", "f5");
    }

    @Test
    public void moveOneSquare() {
        assertTrue(whitePawn.move("e5"));
        assertEquals("e5", whitePawn.getPosition());

        assertTrue(blackPawn.move("f4"));
        assertEquals("f4", blackPawn.getPosition());
    }

    @Test
    public void moveTwoSquare() {
        assertTrue(whitePawn.move("e6"));
        assertEquals("e6", whitePawn.getPosition());

        assertTrue(blackPawn.move("f3"));
        assertEquals("f3", blackPawn.getPosition());
    }

    @Test
    public void moveTwoSquareAfterInitialMovement() {
        assertTrue(whitePawn.move("e5"));
        assertTrue(blackPawn.move("f4"));
        
        assertFalse(whitePawn.move("e7"));
        assertFalse(blackPawn.move("f2"));
    }

    @Test
    public void moveThreeSquare() {
        assertFalse(whitePawn.move("e7"));
        assertEquals("e4", whitePawn.getPosition());

        assertFalse(blackPawn.move("f2"));
        assertEquals("f5", blackPawn.getPosition());
    }

    @Test
    public void moveBackwards() {
        assertFalse(whitePawn.move("e3"));
        assertEquals("e4", whitePawn.getPosition());

        assertFalse(blackPawn.move("f6"));
        assertEquals("f5", blackPawn.getPosition());
    }

    @Test
    public void moveOutOfBounds() {
        assertFalse(whitePawn.move("i4"));
        assertEquals("e4", whitePawn.getPosition());

        assertFalse(blackPawn.move("i4"));
        assertEquals("f5", blackPawn.getPosition());
    }

    @Test
    public void capturePawnDiagonally() {
        assertTrue(whitePawn.capture("f5"));
        assertEquals("f5", whitePawn.getPosition());
    }

    @Test
    public void capturePawnForward() {
        blackPawn = new Pawn("black", "e5");
        assertFalse(whitePawn.capture("e5"));
        assertEquals("e4", whitePawn.getPosition());
    }
}