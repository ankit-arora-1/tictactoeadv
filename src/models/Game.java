package models;

import exceptions.MoreThanOneBotException;
import exceptions.PlayerCountMismatchException;
import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<WinningStrategy> winningStrategies, List<Player> players) {
        this.winningStrategies = winningStrategies;
        this.players = players;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayerIndex = 0;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        // TODO: Move validation logic in a separate
        private void validateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player: players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if(botCount > 1) {
                throw new MoreThanOneBotException();
            }
        }

        public void validatePlayerCount() throws PlayerCountMismatchException {
            if(players.size() != dimension - 1) {
                throw new PlayerCountMismatchException();
            }
        }

        private void validateUniqueSymbolForPlayers() {

        }

        private void validate() throws MoreThanOneBotException, PlayerCountMismatchException {
            validateBotCount();
            validatePlayerCount();
            validateUniqueSymbolForPlayers();
        }

        public Game build() throws PlayerCountMismatchException, MoreThanOneBotException {
            validate();
            return new Game(dimension, winningStrategies, players);
        }
    }


    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    // Break for 7 minutes: 8:34 -> 8:41
}
