package controllers;

import models.Game;
import models.GameState;
import models.Player;
import strategies.winningstrategy.WinningStrategy;

import java.util.List;

// Controllers are stateless.
public class GameController {

    public Game startGame(int dimensionsOfBoard,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) {
        return null;
    }

    public void makeMove(Game game) {

    }

    public GameState checkState(Game game) {
        return null;
    }

    public Player getWinner(Game game) {
        return null;
    }

    public void printBoard(Game game) {

    }

    public void undo(Game game) {

    }
}
