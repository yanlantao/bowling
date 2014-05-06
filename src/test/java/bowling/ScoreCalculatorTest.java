package bowling;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: lantao.yan
 * Date: 30/04/2014
 */
public class ScoreCalculatorTest {
  FrameController controller = new FrameController();
  ScoreCalculator sut        = new ScoreCalculator(controller);

  @Test
  public void shouldCalculateNonBonusFrameCorrectly() {
    addScore(1, 5, 2, 3);
    int score = sut.calculate();
    assertEquals(11, score);
  }

  @Test
  public void shouldCalculateStrikeCorrectly() {
    addScore(10, 2, 3);
    assertEquals(20, sut.calculate());
  }

  @Test
  public void shouldCalculateSpareCorrectly() {
    addScore(9, 1, 2, 3);
    assertEquals(17, sut.calculate());
  }

  @Test
  public void shouldScore300IfFullStrike() {
    addScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
      10, 10);
    int score = sut.calculate();
    assertEquals(300, score);
  }

  @Test
  public void shouldScoreCorrectlyIfTenthFrameIsSpare() {
    addScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 9,
      1, 5);
    int score = sut.calculate();
    assertEquals(274, score);
  }

  private void addScore(int... values) {
    for (int eachValue : values) {
      controller.addScore(new Score(eachValue));
    }
  }
}
