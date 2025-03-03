package com.chessonline;

public class MoveHandler {
    private ChessBoard chessBoard;

    public MoveHandler(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean movePiece(String fromPosition, String toPosition) {
        int[] fromCoords = chessBoard.positionToCoordinates(fromPosition);
        int[] toCoords = chessBoard.positionToCoordinates(toPosition);
    
        if (chessBoard.isDestinationEmpty(toPosition)) {
            return handleMove(fromCoords, toCoords, toPosition);
        }
        
        chessBoard.isCheckmate(chessBoard.getWhiteKing());
        
        return handleCapture(toCoords, fromCoords, toPosition);
    }

    private boolean handleMove(int[] fromCoords, int[] toCoords, String toPosition) {
        Piece piece = chessBoard.getBoard()[fromCoords[0]][fromCoords[1]];

        if (!piece.move(toPosition)) {
            return false;
        }
    
        chessBoard.updateBoard(fromCoords, toCoords, piece);

        if (piece instanceof Pawn) {
            int lastRow = piece.getColor().equals("white") ? 8 : 1;
            if (toPosition.charAt(1) - '0' == lastRow) {
                chessBoard.promotePawn((Pawn) piece);
            }
        }

        chessBoard.isCheckmate(chessBoard.getWhiteKing());

        return true;
    }

    private boolean handleCapture(int[] toCoords, int[] fromCoords, String toPosition) {
        Piece piece = chessBoard.getBoard()[fromCoords[0]][fromCoords[1]];
        Piece targetPiece = chessBoard.getBoard()[toCoords[0]][toCoords[1]];
        
        if (piece.getColor().equals(targetPiece.getColor())) return false;

        if (!piece.isCaptureMovementValid(toPosition)) return false;

        chessBoard.removePiece(targetPiece);
        chessBoard.updateBoard(fromCoords, toCoords, piece);

        return true;
    }
}
