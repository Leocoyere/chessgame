from .piece import Piece


class Rook(Piece):
    def __init__(self, color: str, position: str):
        super().__init__("rook", color, position)

    def move(self, new_position: str) -> bool:
        if not self.is_movement_valid(new_position):
            return False

        self.set_position(new_position)
        return True

    def is_movement_valid(self, new_position: str) -> bool:
        current_col = self.position[0]
        current_row = int(self.position[1])

        target_col = new_position[0]
        target_row = int(new_position[1])

        # must move along a rank (same row) or a file (same column)
        return current_col == target_col or current_row == target_row

    def is_capture_movement_valid(self, new_position: str) -> bool:
        return self.is_movement_valid(new_position)