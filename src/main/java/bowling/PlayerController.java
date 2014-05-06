package bowling;

/**
 * User: lantao.yan
 * Date: 01/05/2014
 */
public class PlayerController {
  private final Player player;
  private final ScoreCalculator scoreCalculator;
  private final FrameController frameController;

  public PlayerController(Player player) {
    this.player = player;
    this.frameController = new FrameController();
    this.scoreCalculator = new ScoreCalculator(frameController);
  }

  public int calculate() {
    return scoreCalculator.calculate();
  }

  public int currentFrameIndex() {
    return frameController.currentFrameIndex();
  }

  public void addScore(Score score) {
    frameController.addScore(score);
  }

  public Player getPlayer() {
    return player;
  }
}
