from ..pieces.piece import Position


class CheckmateHandler:
    def __init__(self, chess_board):
        self.chess_board = chess_board

    def is_king_in_check(self, king):
        if not self.get_threatening_pieces(king):
            return False

        print(f"Check for {king.get_color()} King")
        return True

    def get_threatening_pieces(self, king):
        threats = []
        king_color = king.get_color()
        king_position = king.get_position()

        opponent_pieces = (
            self.chess_board.get_black_pieces()
            if king_color == "white"
            else self.chess_board.get_white_pieces()
        )

        for piece in opponent_pieces:
            if piece.is_capture_movement_valid(king_position):
                threats.append(piece)

        return threats

    def is_checkmate(self, king):
        if not self.is_king_in_check(king):
            return False

        if self.can_king_escape(king):
            return False

        if self.can_block_or_capture_threats(king):
            return False

        print(f"Checkmate for {king.get_color()} King")
        return True

    def can_king_escape(self, king):
        moves = king.get_possible_moves()

        for move in moves:
            if (
                not self.chess_board.is_destination_empty(move)
                and self.chess_board.is_destination_ally(move, king.get_color())
            ):
                continue

            if self.is_square_in_check(move, king):
                continue

            return True

        return False

    def is_square_in_check(self, position, king):
        count = 0

        self.chess_board.remove_piece(king)

        opponent_pieces = (
            self.chess_board.get_black_pieces()
            if king.get_color() == "white"
            else self.chess_board.get_white_pieces()
        )

        for piece in opponent_pieces:
            if piece.is_capture_movement_valid(position):
                count += 1

        self.chess_board.place_piece(king)

        return count > 0

    def can_block_or_capture_threats(self, king):
        threats = self.get_threatening_pieces(king)

        if len(threats) > 1:
            return False

        threat = threats[0]
        return self.handle_threat(threat)

    def handle_threat(self, threat_piece):
        ally_pieces = (
            self.chess_board.get_black_pieces()
            if threat_piece.get_color() == "white"
            else self.chess_board.get_white_pieces()
        )

        # can capture the threatening piece
        for ally in ally_pieces:
            if ally.is_capture_movement_valid(threat_piece.get_position()):
                return True

        # knight cannot be blocked (knights jump over pieces)
        # TODO: replace with isinstance(threat_piece, Knight) once Knight is implemented
        if threat_piece.get_name() == "knight":
            return False

        return self.is_block_possible(threat_piece)

    def is_block_possible(self, threat_piece):
        king = (
            self.chess_board.get_black_king()
            if threat_piece.get_color() == "white"
            else self.chess_board.get_white_king()
        )

        king_pos = Position(king.get_position())
        threat_pos = Position(threat_piece.get_position())

        path = self.chess_board.get_path_between(king_pos, threat_pos)

        ally_pieces = (
            self.chess_board.get_black_pieces()
            if threat_piece.get_color() == "white"
            else self.chess_board.get_white_pieces()
        )

        for ally in ally_pieces:
            for pos in path:
                if ally.is_movement_valid(pos):
                    return True

        return False