from typing import List
from .pieces import King, Pawn
from .helpers.move_handler import MoveHandler
from .helpers.checkmate_handler import CheckmateHandler

class ChessBoard:
    def __init__(self):
        self.board = [[None for _ in range(8)] for _ in range(8)]
        self.white_pieces: List = []
        self.black_pieces: List = []
        self.white_king = None
        self.black_king = None

        self.move_handler = MoveHandler(self)
        self.checkmate_handler = CheckmateHandler(self)

    def get_board(self):
        return self.board

    def get_white_pieces(self):
        return self.white_pieces

    def get_black_pieces(self):
        return self.black_pieces

    def get_white_king(self):
        return self.white_king

    def get_black_king(self):
        return self.black_king

    def initialize_board(self):
        for i in range(8):
            column = chr(ord('a') + i)
            self.place_piece(Pawn("black", f"{column}7"))

        self.white_king = King("white", "e1")
        self.place_piece(self.white_king)

        for i in range(8):
            column = chr(ord('a') + i)
            self.place_piece(Pawn("white", f"{column}2"))

        self.black_king = King("black", "e8")
        self.place_piece(self.black_king)

    def update_board(self, from_coords, to_coords, piece):
        self.board[to_coords[0]][to_coords[1]] = piece
        self.board[from_coords[0]][from_coords[1]] = None

        new_position = f"{chr(ord('a') + to_coords[1])}{8 - to_coords[0]}"
        piece.set_position(new_position)

    def display_board(self):
        for i in range(8):
            for j in range(8):
                if self.board[i][j] is None:
                    print("[  ]", end=" ")
                else:
                    piece = self.board[i][j]
                    color = piece.get_color()[0].upper()
                    name = piece.get_name()[0].upper()
                    print(f"[{color}{name}]", end=" ")
            print()

    def get_piece_at(self, position):
        coords = self.position_to_coordinates(position)
        return self.board[coords[0]][coords[1]]

    def place_piece(self, piece):
        coords = self.position_to_coordinates(piece.get_position())
        self.board[coords[0]][coords[1]] = piece

        if piece.get_name() == "king":
            return

        if piece.get_color() == "white":
            self.white_pieces.append(piece)
        else:
            self.black_pieces.append(piece)

    def remove_piece(self, piece):
        coords = self.position_to_coordinates(piece.get_position())
        self.board[coords[0]][coords[1]] = None

        if piece.get_color() == "white":
            self.white_pieces.remove(piece)
        else:
            self.black_pieces.remove(piece)

    def move_piece(self, from_position, to_position):
        return self.move_handler.move_piece(from_position, to_position)

    def is_destination_empty(self, position):
        return self.get_piece_at(position) is None

    def is_destination_ally(self, position, color):
        piece = self.get_piece_at(position)
        return piece is not None and piece.get_color() == color

    def is_king_in_check(self, king):
        return self.checkmate_handler.is_king_in_check(king)

    def is_checkmate(self, king):
        return self.checkmate_handler.is_checkmate(king)

    def get_path_between(self, from_pos, to_pos):
        path = []

        col_step = (to_pos.col > from_pos.col) - (to_pos.col < from_pos.col)
        row_step = (to_pos.row > from_pos.row) - (to_pos.row < from_pos.row)

        col = chr(ord(from_pos.col) + col_step)
        row = from_pos.row + row_step

        while col != to_pos.col or row != to_pos.row:
            path.append(f"{col}{row}")
            col = chr(ord(col) + col_step)
            row += row_step

        return path

    def position_to_coordinates(self, position):
        row = 8 - int(position[1])
        col = ord(position[0]) - ord('a')
        return [row, col]