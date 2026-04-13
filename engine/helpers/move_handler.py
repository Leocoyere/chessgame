from ..pieces.pawn import Pawn


class MoveHandler:
    def __init__(self, chess_board):
        self.chess_board = chess_board

    def move_piece(self, from_position, to_position):
        from_coords = self.chess_board.position_to_coordinates(from_position)
        to_coords = self.chess_board.position_to_coordinates(to_position)

        if self.chess_board.is_destination_empty(to_position):
            return self.handle_move(from_coords, to_coords, to_position)

        # checkmate check (same as Java, even if odd placement)
        self.chess_board.is_checkmate(self.chess_board.get_white_king())

        return self.handle_capture(to_coords, from_coords, to_position)

    def handle_move(self, from_coords, to_coords, to_position):
        piece = self.chess_board.get_board()[from_coords[0]][from_coords[1]]

        if not piece.move(to_position):
            return False

        self.chess_board.update_board(from_coords, to_coords, piece)

        # pawn promotion
        if isinstance(piece, Pawn):
            last_row = 8 if piece.get_color() == "white" else 1
            if int(to_position[1]) == last_row:
                self.chess_board.promote_pawn(piece)

        self.chess_board.is_checkmate(self.chess_board.get_white_king())

        return True

    def handle_capture(self, to_coords, from_coords, to_position):
        piece = self.chess_board.get_board()[from_coords[0]][from_coords[1]]
        target_piece = self.chess_board.get_board()[to_coords[0]][to_coords[1]]

        if piece.get_color() == target_piece.get_color():
            return False

        if not piece.is_capture_movement_valid(to_position):
            return False

        self.chess_board.remove_piece(target_piece)
        self.chess_board.update_board(from_coords, to_coords, piece)

        return True