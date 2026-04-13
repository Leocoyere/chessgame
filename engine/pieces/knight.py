from .piece import Piece

class Knight(Piece):
    def __init__(self, color: str, position: str):
        super().__init__("knight", color, position)

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

        col_diff = abs(ord(target_col) - ord(current_col))
        row_diff = abs(target_row - current_row)

        # L-shape movement
        return (col_diff == 2 and row_diff == 1) or (col_diff == 1 and row_diff == 2)

    def is_capture_movement_valid(self, new_position: str) -> bool:
        return self.is_movement_valid(new_position)