from django.db import models

class Game(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    board_state = models.JSONField()
    turn = models.CharField(max_length=5, default="white")
    status = models.CharField(max_length=10, default="ongoing")

    def __str__(self):
        return f"Game {self.id} - {self.status}"