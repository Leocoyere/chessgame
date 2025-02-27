package com.chessonline;

import java.util.ArrayList;
import java.util.List;

import com.chessonline.Piece.Position;

public class CheckmateHandler {
    private ChessBoard chessBoard;

    public CheckmateHandler(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean isKingInCheck(King king) {
        if (getThreateningPieces(king).isEmpty()) return false;

        System.out.println("Check for " + king.getColor() + " King");
        return true;
    }

    public List<Piece> getThreateningPieces(King king) {
        List<Piece> threats = new ArrayList<>();
        String kingColor = king.getColor();
        String kingPosition = king.getPosition();
    
        List<Piece> opponentPieces = kingColor.equals("white") ? chessBoard.getBlackPieces() : chessBoard.getWhitePieces();
    
        for (Piece piece : opponentPieces) {
            if (piece.isCaptureMovementValid(kingPosition)) {
                threats.add(piece);
            }
        }
        return threats;
    }

    public boolean isCheckmate(King king) {
        if (!isKingInCheck(king)) return false;
        if (canKingEscape(king)) return false;
        if (canBlockOrCaptureThreats(king)) return false;

        System.out.println("Checkmate for " + king.getColor() + " King");
        return true;
    }

    private boolean canKingEscape(King king) {
        List<String> moves = king.getPossibleMoves();
        for (String move : moves) {
            if (!chessBoard.isDestinationEmpty(move) && chessBoard.isDestinationAlly(move, king.getColor())) continue;
            if (isSquareInCheck(move, king)) continue;
            return true;
        }
        return false;
    }

    private boolean isSquareInCheck(String position, King king) {
        
        int count = 0;
        chessBoard.removePiece(king);

        List<Piece> opponentPieces = king.getColor().equals("white") ? chessBoard.getBlackPieces() : chessBoard.getWhitePieces();
    
        for (Piece piece : opponentPieces) {
            if (piece.isCaptureMovementValid(position)) count++;
        }

        chessBoard.placePiece(king);
        return count > 0;
    }

    private boolean canBlockOrCaptureThreats(King king) {
        List<Piece> threats = getThreateningPieces(king);
        if (threats.size() > 1) return false;
        Piece threat = threats.get(0);
        return handleThreat(threat);
    }

    public boolean handleThreat(Piece threatPiece) {

        List<Piece> allyPieces = (threatPiece.getColor().equals("white")) ? chessBoard.getBlackPieces() : chessBoard.getWhitePieces();
    
        for (Piece ally : allyPieces) {
            if (ally.isCaptureMovementValid(threatPiece.getPosition())) {
                return true;
            }
        }

        if (threatPiece instanceof Knight) return false;
    
        return isBlockPossible(threatPiece);
    }

    private boolean isBlockPossible(Piece threatPiece) {
        King king = (threatPiece.getColor().equals("white")) ? chessBoard.getBlackKing() : chessBoard.getWhiteKing();
        Position kingPos = new Position(king.getPosition());
        Position threatPos = new Position(threatPiece.getPosition());
    
        List<String> path = chessBoard.getPathBetween(kingPos, threatPos);
    
        for (Piece ally : (threatPiece.getColor().equals("white") ? chessBoard.getBlackPieces() : chessBoard.getWhitePieces())) {
            for (String pos : path) {
                if (ally.isMovementValid(pos)) {
                    return true;
                }
            }
        }
    
        return false;
    }
}
