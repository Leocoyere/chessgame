package com.chessonline;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chessonline.Piece.Position;

public class ChessBoard {
    private Piece[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private King whiteKing;
    private King blackKing;
    private MoveHandler moveHandler;
    private CheckmateHandler checkmateHandler;

    public ChessBoard() {
        board = new Piece[8][8];
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        moveHandler = new MoveHandler(this);
        checkmateHandler = new CheckmateHandler(this);
    }

    public Piece[][] getBoard() {
        return board;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public void initializeBoard() {

        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            placePiece(new Pawn("black", column + "7"));
        }
        placePiece(new Rook("white", "a1", this));
        placePiece(new Rook("white", "h1", this));

        whiteKing = new King("white", "e1");
        placePiece(whiteKing);

        for (int i = 0; i < 8; i++) {
            char column = (char) ('a' + i);
            placePiece(new Pawn("white", column + "2"));
        }
        placePiece(new Rook("black", "a8", this));
        placePiece(new Rook("black", "h8", this));

        blackKing = new King("black", "e8");
        placePiece(blackKing);
    }

    public void updateBoard(int[] fromCoords, int[] toCoords, Piece piece) {
        board[toCoords[0]][toCoords[1]] = piece;
        board[fromCoords[0]][fromCoords[1]] = null;
        piece.setPosition("" + (char) ('a' + toCoords[1]) + (8 - toCoords[0]));
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("[  ] ");
                } else {
                    System.out.print("[" + board[i][j].getColor().substring(0, 1).toUpperCase() + board[i][j].getName().substring(0, 1).toUpperCase() + "] ");
                }
            }
            System.out.println();
        }
    }

    public Piece getPieceAt(String position) {
        int[] coords = positionToCoordinates(position);
        return board[coords[0]][coords[1]];
    }

    public void placePiece(Piece piece) {
        int[] coords = positionToCoordinates(piece.getPosition());
        board[coords[0]][coords[1]] = piece;

        if(piece.getName().equals("king")) return;

        if (piece.getColor().equals("white")) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    public void removePiece(Piece piece) {
        int[] coords = positionToCoordinates(piece.getPosition());
        board[coords[0]][coords[1]] = null;

        if (piece.getColor().equals("white")) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

    public boolean movePiece(String fromPosition, String toPosition) {
        return moveHandler.movePiece(fromPosition, toPosition);
    }

    public void promotePawn(Pawn pawn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Votre pion a atteint l'autre côté ! Choisissez une promotion : (Q)ueen, (R)ook, (B)ishop, (K)night");
        
        String choice;
        Piece newPiece = null;
        String color = pawn.getColor();
        String position = pawn.getPosition();
        
        do {
            choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "Q":
                    newPiece = new Queen(color, position, this);
                    break;
                case "R":
                    newPiece = new Rook(color, position, this);
                    break;
                case "B":
                    newPiece = new Bishop(color, position, this);
                    break;
                case "K":
                    newPiece = new Knight(color, position);
                    break;
                default:
                    System.out.println("Choix invalide, entrez Q, R, B ou N :");
            }
        } while (newPiece == null);

        removePiece(pawn);
        placePiece(newPiece);
        System.out.println("Pion promu en " + newPiece.getClass().getSimpleName() + " !");
        displayBoard();
    }
    
    public boolean isDestinationEmpty(String position) {
        return getPieceAt(position) == null;
    }

    public boolean isDestinationAlly(String position, String color) {
        Piece piece = getPieceAt(position);
        return piece != null && piece.getColor().equals(color);
    }

    public boolean isKingInCheck(King king) {
        return checkmateHandler.isKingInCheck(king);
    }

    public boolean isCheckmate(King king) {
        return checkmateHandler.isCheckmate(king);
    }

    public List<String> getPathBetween(Position from, Position to) {
        List<String> path = new ArrayList<>();
    
        int colStep = Integer.compare(to.col, from.col);
        int rowStep = Integer.compare(to.row, from.row);
    
        char col = (char) (from.col + colStep);
        int row = from.row + rowStep;
    
        while (col != to.col || row != to.row) {
            path.add("" + col + row);
            col += colStep;
            row += rowStep;
        }
    
        return path;
    }

    public int[] positionToCoordinates(String position) {
        int row = 8 - Integer.parseInt(position.substring(1));
        int col = position.charAt(0) - 'a';
        return new int[] { row, col };
    }
}