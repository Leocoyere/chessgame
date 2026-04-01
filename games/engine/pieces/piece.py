from abc import ABC, abstractmethod

class Piece(ABC):
    def __init__(self, name: str, color: str, position: str):
        self.name = name
        self.color = color
        self.position = position

    @abstractmethod
    def move(self, new_position: str) -> bool:
        pass

    @abstractmethod
    def is_movement_valid(self, new_position: str) -> bool:
        pass

    @abstractmethod
    def is_capture_movement_valid(self, new_position: str) -> bool:
        pass

    def get_name(self) -> str:
        return self.name

    def get_color(self) -> str:
        return self.color

    def get_position(self) -> str:
        return self.position

    def set_position(self, position: str):
        self.position = position

class Position:
    def __init__(self, position: str):
        self.col = position[0]
        self.row = int(position[1])