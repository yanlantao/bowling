package bowling;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: lantao yan
 * Date: 4/30/14
 */
public class FrameControllerTest {
  FrameController sut = new FrameController();

  @Test
  public void shouldReturnFrameOneIfNoScoreSubmitted() {
    int currentFrame = sut.currentFrameIndex();
    assertEquals(0, currentFrame);
  }

  @Test
  public void shouldReturnFrameOneIfFirstShotLessThanTen() {
    sut.addScore(new Score(9));
    int currentFrame = sut.currentFrameIndex();
    assertEquals(0, currentFrame);
  }

  @Test
  public void shouldReturnFrameTwoIfMakeTwoShots() {
    sut.addScore(new Score(5));
    sut.addScore(new Score(4));

    int currentFrame = sut.currentFrameIndex();
    assertEquals(1, currentFrame);
  }

  @Test
  public void shouldReturnFrameTwoIfMakeSpike() {
    sut.addScore(new Score(10));

    int currentFrame = sut.currentFrameIndex();
    assertEquals(1, currentFrame);
  }

  @Test
  public void shouldReturnNextShotCorrectly() {
    sut.addScore(new Score(10));
    sut.addScore(new Score(9));
    Shot shot = sut.nextShot(0);
    assertEquals(9, shot.score());
  }

  @Test
  public void shouldReturnNextTwoShotCorrectly() {
    sut.addScore(new Score(10));
    sut.addScore(new Score(9));
    sut.addScore(new Score(1));
    Shot[] shot = sut.nextTwoShots(0);
    assertEquals(2, shot.length);
    assertEquals(9, shot[0].score());
    assertEquals(1, shot[1].score());
  }

  @Test
  public void shouldReturnNextTwoShotCorrectlyWhenNextFrameNotExist() {
    sut.addScore(new Score(10));
    sut.addScore(new Score(10));
    Shot[] shot = sut.nextTwoShots(0);
    assertEquals(1, shot.length);
    assertEquals(10, shot[0].score());
  }

  @Test
  public void shouldReturnNextTwoShotCorrectlyWhenNextTwoShotsNotExist() {
    sut.addScore(new Score(10));
    Shot[] shot = sut.nextTwoShots(0);
    assertEquals(0, shot.length);
  }

  @Test
  public void shouldReturnNextTwoShotCorrectlyWhenGivenFullScore() {
    givenFullScore();
    Shot[] shot = sut.nextTwoShots(8);
    assertEquals(2, shot.length);
    assertEquals(10, shot[0].score());
    assertEquals(10, shot[1].score());
  }

  @Test
  public void shouldReturnFrameMaxWhenGivenFullScore() {
    givenFullScore();
    int currentFrameIndex = sut.currentFrameIndex();
    assertEquals(10, currentFrameIndex);
  }

  private void givenFullScore() {
    for (int i = 0; i < 12; i++) {
      sut.addScore(new Score(10));
    }
  }
}
