from .piece import Piece

class Pawn(Piece):
    def __init__(self, color: str, position: str):
        super().__init__("pawn", color, position)
        self.move_count = 0

    def move(self, new_position: str) -> bool:
        if not self.is_movement_valid(new_position):
            return False

        self.set_position(new_position)
        self.move_count += 1

        return True

    def is_movement_valid(self, new_position: str) -> bool:
        current_row = int(self.position[1])
        new_row = int(new_position[1])

        current_col = self.position[0]
        new_col = new_position[0]

        direction = 1 if self.color == "white" else -1

        # move forward 1
        if new_col == current_col and new_row == current_row + direction:
            return True

        # move forward 2 (first move)
        if (
            new_col == current_col
            and new_row == current_row + 2 * direction
            and self.move_count == 0
        ):
            return True

        return False

    def is_capture_movement_valid(self, new_position: str) -> bool:
        current_row = int(self.position[1])
        new_row = int(new_position[1])

        current_col = self.position[0]
        new_col = new_position[0]

        direction = 1 if self.color == "white" else -1

        # diagonal capture
        if abs(ord(new_col) - ord(current_col)) == 1 and new_row == current_row + direction:
            return True

        return False