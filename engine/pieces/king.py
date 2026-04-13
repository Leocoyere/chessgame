from .piece import Piece

class King(Piece):
    def __init__(self, color: str, position: str):
        super().__init__("king", color, position)

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

        row_diff = abs(target_row - current_row)
        col_diff = abs(ord(target_col) - ord(current_col))

        if row_diff > 1 or col_diff > 1:
            return False

        return True

    def is_capture_movement_valid(self, new_position: str) -> bool:
        return self.is_movement_valid(new_position)

    def get_possible_moves(self):
        moves = []

        directions = [
            (1, 0), (-1, 0), (0, 1), (0, -1),
            (1, 1), (-1, -1), (1, -1), (-1, 1)
        ]

        current_col = self.position[0]
        current_row = int(self.position[1])

        for dcol, drow in directions:
            new_col = chr(ord(current_col) + dcol)
            new_row = current_row + drow

            if self.is_within_bounds(new_col, new_row):
                moves.append(f"{new_col}{new_row}")

        return moves

    def is_within_bounds(self, col, row):
        return 'a' <= col <= 'h' and 1 <= row <= 8