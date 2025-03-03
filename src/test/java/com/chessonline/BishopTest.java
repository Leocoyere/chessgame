package com.chessonline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {
    private ChessBoard chessBoard;
    private Bishop whiteBishop;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard();
        whiteBishop = new Bishop("white", "a1", chessBoard);
        chessBoard.placePiece(whiteBishop);
    }

    @Test
    public void moveDiagonally() {
        assertTrue(chessBoard.movePiece(whiteBishop.getPosition(), "h8"));
        assertEquals("h8", whiteBishop.getPosition());
    }

    @Test
    public void moveBlockedByPiece() {
        chessBoard.placePiece(new Pawn("white", "b2"));
        assertFalse(chessBoard.movePiece(whiteBishop.getPosition(), "h8"));
        assertEquals("a1", whiteBishop.getPosition());
    }

    @Test
    public void moveHorizontallyOrVertically() {
        assertFalse(chessBoard.movePiece(whiteBishop.getPosition(), "h1"));
        assertFalse(chessBoard.movePiece(whiteBishop.getPosition(), "a8"));
        assertEquals("a1", whiteBishop.getPosition());
    }

    @Test
    public void captureEnemyPiece() {
        chessBoard.placePiece(new Pawn("black", "g7"));
        assertTrue(chessBoard.movePiece(whiteBishop.getPosition(), "g7"));
        assertEquals("g7", whiteBishop.getPosition());
    }


}
