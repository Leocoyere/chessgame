import unittest

from engine.board import ChessBoard
from engine.pieces.pawn import Pawn

class ChessBoardInitTest(unittest.TestCase):
    """Tests for initialize_board() — verifies the starting position is correct."""

    def setUp(self):
        self.board = ChessBoard()
        self.board.initialize_board()

    def test_white_pawns_are_on_row_2(self):
        for col in "abcdefgh":
            with self.subTest(square=f"{col}2"):
                piece = self.board.get_piece_at(f"{col}2")
                self.assertIsNotNone(piece)
                self.assertEqual(piece.get_name(), "pawn")
                self.assertEqual(piece.get_color(), "white")

    def test_black_pawns_are_on_row_7(self):
        for col in "abcdefgh":
            with self.subTest(square=f"{col}7"):
                piece = self.board.get_piece_at(f"{col}7")
                self.assertIsNotNone(piece)
                self.assertEqual(piece.get_name(), "pawn")
                self.assertEqual(piece.get_color(), "black")

    def test_white_king_is_on_e1(self):
        piece = self.board.get_piece_at("e1")
        self.assertIsNotNone(piece)
        self.assertEqual(piece.get_name(), "king")
        self.assertEqual(piece.get_color(), "white")

    def test_black_king_is_on_e8(self):
        piece = self.board.get_piece_at("e8")
        self.assertIsNotNone(piece)
        self.assertEqual(piece.get_name(), "king")
        self.assertEqual(piece.get_color(), "black")

    def test_middle_rows_are_empty(self):
        for col in "abcdefgh":
            for row in range(3, 7):
                with self.subTest(square=f"{col}{row}"):
                    self.assertIsNone(self.board.get_piece_at(f"{col}{row}"))

    def test_white_pieces_list_has_eight_pawns(self):
        self.assertEqual(len(self.board.get_white_pieces()), 8)

    def test_black_pieces_list_has_eight_pawns(self):
        self.assertEqual(len(self.board.get_black_pieces()), 8)


class ChessBoardMoveTest(unittest.TestCase):
    """Tests for move_piece() — valid moves, invalid moves, and captures."""

    def setUp(self):
        self.board = ChessBoard()
        self.board.initialize_board()

    # --- Valid moves ---

    def test_move_piece_places_piece_at_destination(self):
        pawn = self.board.get_piece_at("e2")
        self.board.move_piece("e2", "e4")
        self.assertEqual(self.board.get_piece_at("e4"), pawn)

    def test_move_piece_clears_origin_square(self):
        self.board.move_piece("e2", "e4")
        self.assertIsNone(self.board.get_piece_at("e2"))

    def test_move_piece_updates_piece_position(self):
        pawn = self.board.get_piece_at("e2")
        self.board.move_piece("e2", "e4")
        self.assertEqual(pawn.get_position(), "e4")

    def test_move_piece_returns_true_on_success(self):
        self.assertTrue(self.board.move_piece("e2", "e4"))

    # --- Invalid moves ---

    def test_move_piece_returns_false_for_invalid_move(self):
        # Pawn cannot move backward
        self.assertFalse(self.board.move_piece("e2", "e1"))

    def test_invalid_move_does_not_change_board(self):
        pawn = self.board.get_piece_at("e2")
        self.board.move_piece("e2", "e5")  # 3 squares forward — invalid
        self.assertEqual(self.board.get_piece_at("e2"), pawn)
        self.assertIsNone(self.board.get_piece_at("e5"))

    # --- Captures ---

    def test_capture_removes_enemy_piece_from_board(self):
        # Place a black pawn on d3 so the white pawn on e2 can capture it
        enemy = Pawn("black", "d3")
        self.board.place_piece(enemy)

        self.board.move_piece("e2", "d3")

        self.assertNotIn(enemy, self.board.get_black_pieces())

    def test_capture_places_attacker_on_target_square(self):
        attacker = self.board.get_piece_at("e2")
        self.board.place_piece(Pawn("black", "d3"))

        self.board.move_piece("e2", "d3")

        self.assertEqual(self.board.get_piece_at("d3"), attacker)

    def test_capture_clears_attacker_origin_square(self):
        self.board.place_piece(Pawn("black", "d3"))
        self.board.move_piece("e2", "d3")
        self.assertIsNone(self.board.get_piece_at("e2"))

    def test_cannot_capture_ally_piece(self):
        # d2 already has a white pawn — e2 pawn cannot capture it
        self.assertFalse(self.board.move_piece("e2", "d2"))


class ChessBoardRemoveTest(unittest.TestCase):
    """Tests for remove_piece()."""

    def setUp(self):
        self.board = ChessBoard()
        self.board.initialize_board()

    def test_remove_piece_clears_square(self):
        pawn = self.board.get_piece_at("e2")
        self.board.remove_piece(pawn)
        self.assertIsNone(self.board.get_piece_at("e2"))

    def test_remove_piece_removes_from_pieces_list(self):
        pawn = self.board.get_piece_at("e2")
        self.board.remove_piece(pawn)
        self.assertNotIn(pawn, self.board.get_white_pieces())
