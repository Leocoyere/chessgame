from django.test import TestCase
from games.models import Game

class GameModelTest(TestCase):
    def test_create_game(self):
        game = Game.objects.create()

        self.assertIsNotNone(game.id)