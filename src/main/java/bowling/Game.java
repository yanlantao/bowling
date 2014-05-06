package bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: lantao yan
 * Date: 4/30/14
 */
public class Game {

  private final List<PlayerController> playerControllerList;
  private       int                    currentPlayerIndex;
  private       int                    currentFrameIndex;

  private Game(Player... players) {
    this.playerControllerList = new ArrayList<PlayerController>(players.length);
    for (Player eachPlayer : players) {
      this.playerControllerList.add(new PlayerController(eachPlayer));
    }
    currentPlayerIndex = 0;
    currentFrameIndex = 0;
  }

  public static Game startGame(Player... players) {
    return new Game(players);
  }

  public int scoreForCurrentPlayer() {
    return playerControllerList.get(currentPlayerIndex).calculate();
  }

  private int frameIndexForCurrentPlayer() {
    return playerControllerList.get(currentPlayerIndex).currentFrameIndex();
  }

  public void submitScore(Score score) {
    playerControllerList.get(currentPlayerIndex).addScore(score);
    if (currentFrameIndex < frameIndexForCurrentPlayer()) {
      moveToNextPlayer();
    }
  }

  private void moveToNextPlayer() {
    if (currentPlayerIndex == playerControllerList.size() - 1) {
      currentPlayerIndex = 0;
      currentFrameIndex++;
    } else {
      currentPlayerIndex++;
    }
  }

  public Player currentPlayer() {
    return playerControllerList.get(currentPlayerIndex).getPlayer();
  }

  public int currentFrameIndex() {
    return currentFrameIndex;
  }

  public boolean isOver() {
    return currentFrameIndex == 10;
  }

  public Map<Player, Integer> finalResult() {
    Map<Player, Integer> results = new HashMap<Player, Integer>();
    for (PlayerController eachPlayerController : playerControllerList) {
      results.put(eachPlayerController.getPlayer(), eachPlayerController.calculate());
    }
    return results;
  }
}