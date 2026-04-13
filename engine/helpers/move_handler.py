from ..pieces.pawn import Pawn
from ..pieces.piece import Position

class MoveHandler:
    def __init__(self, chess_board):
        self.chess_board = chess_board

    def move_piece(self, from_position, to_position):
        from_coords = self.chess_board.position_to_coordinates(from_position)
        to_coords = self.chess_board.position_to_coordinates(to_position)

        if self.chess_board.is_destination_empty(to_position):
            return self.handle_move(from_coords, to_coords, to_position)

        self.chess_board.is_checkmate(self.chess_board.get_white_king())

        return self.handle_capture(to_coords, from_coords, to_position)

    def handle_move(self, from_coords, to_coords, to_position):
        piece = self.chess_board.get_board()[from_coords[0]][from_coords[1]]

        if not piece.is_movement_valid(to_position):
            return False

        if not self._is_path_clear(piece.get_position(), to_position, piece):
            return False

        piece.move(to_position)
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

        # Path to the target must also be clear (excludes the destination itself)
        if not self._is_path_clear(piece.get_position(), to_position, piece):
            return False

        self.chess_board.remove_piece(target_piece)
        self.chess_board.update_board(from_coords, to_coords, piece)

        return True

    def _is_path_clear(self, from_position, to_position, piece):
        if piece.get_name() == "knight":
            return True

        from_pos = Position(from_position)
        to_pos = Position(to_position)

        # get_path_between returns squares strictly between origin and destination (exclusive)
        path = self.chess_board.get_path_between(from_pos, to_pos)

        for square in path:
            if not self.chess_board.is_destination_empty(square):
                return False

        return True