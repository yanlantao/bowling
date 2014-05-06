package bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: lantao yan
 * Date: 4/29/14
 */
public class TenPinBowlingEndToEndTest {

  @Test
  public void shouldScoreZeroWhenHitNoPins() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(0));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(0, totalScore);
  }

  @Test
  public void shouldScoreWhenHitPinsInOneFrame() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(1));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(1, totalScore);
  }

  @Test
  public void shouldScoreRightWhenFinishFirstFrame() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(1));
    game.submitScore(new Score(8));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(9, totalScore);
  }

  @Test
  public void shouldScoreRightWhenFinishFirstTwoFrames() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(1));
    game.submitScore(new Score(8));
    game.submitScore(new Score(1));
    game.submitScore(new Score(8));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(18, totalScore);
  }

  @Test
  public void shouldScoreRightWhenFinishFirstFrameWithStrike() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(10));
    game.submitScore(new Score(1));
    game.submitScore(new Score(8));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(28, totalScore);
  }

  @Test
  public void shouldScoreRightWhenFinishFirstFrameWithSpare() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(7));
    game.submitScore(new Score(3));
    game.submitScore(new Score(1));
    game.submitScore(new Score(8));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(20, totalScore);
  }

  @Test
  public void shouldScoreRightWhenFinishFirstTwoFrameWithStrike() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(10));
    game.submitScore(new Score(10));
    game.submitScore(new Score(8));
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(10 + 10 + 8 + 10 + 8 + 8, totalScore);
  }

  @Test
  public void shouldMake300WhenAllStrike() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    withAllStrike(game);
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(300, totalScore);
  }

  @Test
  public void shouldCalculateCorrectlyWhenTenthGameIsSpare() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    addScoreToGame(game, 10, 10, 10, 10, 10,
      10, 10, 10, 10, 9, 1, 5);
    int totalScore = game.scoreForCurrentPlayer();
    assertEquals(274, totalScore);
  }

  @Test
  public void shouldInFrameZeroWhenJustStartTheGame() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    int frameIndex = game.currentFrameIndex();
    assertEquals(0, frameIndex);
  }

  @Test
  public void shouldInFrameZeroWhenFirstPlayerFinishGameSecondIsWaiting() {
    Game game = Game.startGame(playerWithName("PlayerA"), playerWithName("PlayerB"));
    game.submitScore(new Score(10));
    int frameIndex = game.currentFrameIndex();
    assertEquals(0, frameIndex);
  }

  @Test
  public void shouldInFrameTwoWhenFinishFirstFrame() {
    Player player = playerWithName("PlayerA");
    Game game = Game.startGame(player);
    game.submitScore(new Score(10));
    assertEquals(1, game.currentFrameIndex());
  }

  @Test
  public void shouldInFrameTwoWhenMultiPlayerFinishFrameOne() {
    Game game = Game.startGame(playerWithName("PlayerA"), playerWithName("PlayerB"));
    game.submitScore(new Score(10));
    game.submitScore(new Score(9));
    game.submitScore(new Score(1));

    assertEquals(1, game.currentFrameIndex());
  }

  private void addScoreToGame(Game game, int... values) {
    for (int eachValue : values) {
      game.submitScore(new Score(eachValue));
    }
  }

  private void withAllStrike(Game game) {
    for (int i = 0; i < 12; i++) {
      game.submitScore(new Score(10));
    }
  }

  Player playerWithName(String name) {
    return new Player(name);
  }
}
