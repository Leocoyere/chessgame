package com.chessonline;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private Piece[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private King whiteKing;
    private King blackKing;

    public ChessBoard() {
        board = new Piece[8][8];
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
    }

    public void initializeBoard() {

        // white pieces
            // pawns
        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            placePiece(new Pawn("black", column + "7"));
        }
            // rooks
        placePiece(new Rook("white", "a1", this));
        placePiece(new Rook("white", "h1", this));

            // king
        whiteKing = new King("white", "e1");
        placePiece(whiteKing);

        //  black pieces
            // pawns
        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            placePiece(new Pawn("white", column + "2"));
        }
            // rooks
        placePiece(new Rook("black", "a8", this));
        placePiece(new Rook("black", "h8", this));

            // king
        blackKing = new King("black", "e8");
        placePiece(blackKing);

    }

    public Piece getPieceAt(String position) {
        int[] coords = positionToCoordinates(position);
        return board[coords[0]][coords[1]];
    }

    public void placePiece(Piece piece) {
        int[] coords = positionToCoordinates(piece.getPosition());
        board[coords[0]][coords[1]] = piece;

        if (piece.getColor().equals("white")) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            // System.out.print(i + 1);
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("[  ] ");
                } else {
                    // System.out.print("[" + board[i][j].getPosition() + "] ");
                    System.out.print("[" + board[i][j].getColor().substring(0, 1).toUpperCase() + board[i][j].getName().substring(0, 1).toUpperCase() + "] ");
                }
            }
            System.out.println();
        }
    }

    public boolean movePiece(String fromPosition, String toPosition) {
        int[] fromCoords = positionToCoordinates(fromPosition);
        int[] toCoords = positionToCoordinates(toPosition);
    
        // Vérifier si un pion existe à la position "fromPosition"
        Piece piece = board[fromCoords[0]][fromCoords[1]];
        if (piece == null) {
            System.out.println("Aucun pion à cette position !");
            return false;
        }
    
        if (isDestinationEmpty(piece, toPosition)) {
            return handleMove(piece, fromCoords, toCoords, toPosition);
        }
        
        return handleCapture(piece, toCoords, fromCoords, toPosition);
    }
    
    private boolean isDestinationEmpty(Piece piece, String toPosition) {
        Piece destinationPiece = board[positionToCoordinates(toPosition)[0]][positionToCoordinates(toPosition)[1]];
        return destinationPiece == null;
    }
    
    private boolean handleCapture(Piece piece, int[] toCoords, int[] fromCoords, String toPosition) {

        Piece targetPiece = board[toCoords[0]][toCoords[1]];
        if (piece.getColor().equals(targetPiece.getColor())) return false;

        if (!piece.isCaptureMovementValid(toPosition)) return false;

        // Enlever la pièce ennemie
        board[toCoords[0]][toCoords[1]] = null;
        if (targetPiece.getColor().equals("white")) {
            whitePieces.remove(targetPiece);
        } else {
            blackPieces.remove(targetPiece);
        }
        
        // Mettre à jour la position de la pièce et l'échiquier
        board[toCoords[0]][toCoords[1]] = piece;
        board[fromCoords[0]][fromCoords[1]] = null;
    
        piece.setPosition(toPosition);
        return true;
    }
    
    private boolean handleMove(Piece piece, int[] fromCoords, int[] toCoords, String toPosition) {
        // Vérifier si le mouvement est valide
        if (!piece.move(toPosition)) {
            return false;
        }
    
        // Mettre à jour l'échiquier
        board[toCoords[0]][toCoords[1]] = piece;
        board[fromCoords[0]][fromCoords[1]] = null;
    
        piece.setPosition(toPosition);
        return true;
    }

    private int[] positionToCoordinates(String position) {
        int row = 8 - Integer.parseInt(position.substring(1));  // Conversion de la ligne
        int col = position.charAt(0) - 'a';  // Conversion de la colonne
        return new int[] { row, col };
    }
}
