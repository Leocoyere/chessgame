from django.db import models


class Game(models.Model):
    class Turn(models.TextChoices):
        WHITE = "white"
        BLACK = "black"

    class Status(models.TextChoices):
        ONGOING = "ongoing"
        CHECKMATE = "checkmate"
        DRAW = "draw"

    created_at = models.DateTimeField(auto_now_add=True)
    board_state = models.JSONField(default=dict)
    turn = models.CharField(max_length=10, choices=Turn.choices, default=Turn.WHITE)
    status = models.CharField(max_length=10, choices=Status.choices, default=Status.ONGOING)

    def __str__(self):
        return f"Game {self.id} - {self.status}"