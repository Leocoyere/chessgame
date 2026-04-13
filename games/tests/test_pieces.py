import unittest
from engine.pieces.pawn import Pawn
from engine.pieces.king import King
from engine.pieces.knight import Knight

# unittest.TestCase — no database, no Django. Pure Python logic tests.
# Each method starting with "test_" is automatically run by the test runner.
# setUp() runs before every single test, giving each one a fresh piece.

class PawnMovementTest(unittest.TestCase):

    def setUp(self):
        # A fresh pawn before every test — no state bleeds between tests.
        self.white_pawn = Pawn("white", "e2")
        self.black_pawn = Pawn("black", "e7")

    # --- White pawn forward movement ---

    def test_white_pawn_can_move_one_square_forward(self):
        self.assertTrue(self.white_pawn.is_movement_valid("e3"))

    def test_white_pawn_can_move_two_squares_on_first_move(self):
        self.assertTrue(self.white_pawn.is_movement_valid("e4"))

    def test_white_pawn_cannot_move_two_squares_after_first_move(self):
        self.white_pawn.move("e3")           # first move
        self.assertFalse(self.white_pawn.is_movement_valid("e5"))

    def test_white_pawn_cannot_move_backward(self):
        self.assertFalse(self.white_pawn.is_movement_valid("e1"))

    def test_white_pawn_cannot_move_sideways(self):
        self.assertFalse(self.white_pawn.is_movement_valid("d2"))

    # --- Black pawn moves in the opposite direction ---

    def test_black_pawn_can_move_one_square_forward(self):
        # "forward" for black is decreasing row number
        self.assertTrue(self.black_pawn.is_movement_valid("e6"))

    def test_black_pawn_cannot_move_in_white_direction(self):
        self.assertFalse(self.black_pawn.is_movement_valid("e8"))

    # --- Capture movement (diagonal) ---

    def test_white_pawn_can_capture_diagonally(self):
        self.assertTrue(self.white_pawn.is_capture_movement_valid("d3"))
        self.assertTrue(self.white_pawn.is_capture_movement_valid("f3"))

    def test_white_pawn_cannot_capture_straight_ahead(self):
        self.assertFalse(self.white_pawn.is_capture_movement_valid("e3"))

    def test_black_pawn_can_capture_diagonally(self):
        self.assertTrue(self.black_pawn.is_capture_movement_valid("d6"))
        self.assertTrue(self.black_pawn.is_capture_movement_valid("f6"))

    # --- move() updates position and increments move_count ---

    def test_move_updates_position(self):
        self.white_pawn.move("e3")
        self.assertEqual(self.white_pawn.get_position(), "e3")

    def test_move_increments_move_count(self):
        self.assertEqual(self.white_pawn.move_count, 0)
        self.white_pawn.move("e3")
        self.assertEqual(self.white_pawn.move_count, 1)

    def test_invalid_move_does_not_update_position(self):
        self.white_pawn.move("e1")           # invalid: backward
        self.assertEqual(self.white_pawn.get_position(), "e2")  # unchanged


class KingMovementTest(unittest.TestCase):

    def setUp(self):
        self.king = King("white", "e4")

    # --- One square in any direction ---

    def test_king_can_move_one_square_in_each_direction(self):
        valid_moves = ["e5", "e3", "d4", "f4", "d5", "f5", "d3", "f3"]
        for move in valid_moves:
            with self.subTest(move=move):
                # subTest: if one fails, the rest still run — you see all failures at once
                self.assertTrue(self.king.is_movement_valid(move))

    def test_king_cannot_move_two_squares(self):
        self.assertFalse(self.king.is_movement_valid("e6"))
        self.assertFalse(self.king.is_movement_valid("g4"))

    def test_king_cannot_move_like_a_knight(self):
        self.assertFalse(self.king.is_movement_valid("f6"))

    # --- Capture uses the same rules as regular movement ---

    def test_king_capture_is_same_as_movement(self):
        self.assertTrue(self.king.is_capture_movement_valid("e5"))
        self.assertFalse(self.king.is_capture_movement_valid("e6"))

    # --- get_possible_moves() ---

    def test_king_in_center_has_eight_possible_moves(self):
        moves = self.king.get_possible_moves()
        self.assertEqual(len(moves), 8)

    def test_king_in_corner_has_three_possible_moves(self):
        corner_king = King("white", "a1")
        moves = corner_king.get_possible_moves()
        self.assertEqual(len(moves), 3)

    def test_king_on_edge_has_five_possible_moves(self):
        edge_king = King("white", "a4")
        moves = edge_king.get_possible_moves()
        self.assertEqual(len(moves), 5)

class KnightMovementTest(unittest.TestCase):

    def setUp(self):
        self.knight = Knight("white", "d3")

    def test_knight_can_move_two_columns_one_row(self):
        valid_moves = ["c5", "e5", "c1", "e1"]
        for move in valid_moves:
            with self.subTest(move=move):
                self.assertTrue(self.knight.is_movement_valid(move))
        
    def test_knight_can_move_one_column_two_rows(self):
        valid_moves = ["b4", "f4", "b2", "f2"]
        for move in valid_moves:
            with self.subTest(move=move):
                self.assertTrue(self.knight.is_movement_valid(move))

    def test_knight_cannot_move_straight(self):
        self.assertFalse(self.knight.is_movement_valid("d4"))

    def test_knight_cannot_move_diagonally(self):
        self.assertFalse(self.knight.is_movement_valid("c4"))

    def test_move_updates_position(self):
        self.knight.move("c5")
        self.assertEqual(self.knight.get_position(), "c5")

    def test_invalid_move_does_not_update_position(self):
        self.knight.move("c4")
        self.assertEqual(self.knight.get_position(), "d3")

    def test_capture_movement_is_same_as_regular_movement(self):
        self.assertTrue(self.knight.is_capture_movement_valid("c5"))
        self.assertTrue(self.knight.is_capture_movement_valid("e5"))