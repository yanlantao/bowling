package bowling;

/**
 * User: lantao yan
 * Date: 4/30/14
 */
public class FrameController {
  private Frame[] frames;
  private int     currentFrameIndex;

  public FrameController() {
    frames = new Frame[10];
    for (int i = 0; i < 10; i++) {
      frames[i] = new Frame();
    }
  }

  public void addScore(Score score) {
    Frame currentFrame = frames[this.currentFrameIndex];
    if (isTenthFrame() && currentFrame.allValidShots().length == 3) {
      throw new RuntimeException("Game Over, Can't add any score");
    }

    if (isTenthFrame() && currentFrame.isSpare() && currentFrame.totalScore() != 10) {
      throw new RuntimeException("Game Over, Can't add any score");
    }

    currentFrame.addShot(new Shot(score.value));
    if (!isTenthFrame() && (currentFrame.isStrike() || currentFrame.hasTwoShots())) {
      this.currentFrameIndex++;
    } else if (isTenthFrame()) {
      if (currentFrame.allValidShots().length == 3 || (currentFrame.hasTwoShots() && currentFrame.isNonBonus())) {
        this.currentFrameIndex++;
      }
    }
  }

  private boolean isTenthFrame() {
    return currentFrameIndex == 9;
  }

  public int currentFrameIndex() {
    return this.currentFrameIndex;
  }

  public int totalFrameCount() {
    return 10;
  }

  public Frame getFrame(int i) {
    return frames[i];
  }

  public Shot[] nextTwoShots(int currentFrameIndex) {
    if (!nextFrameExist(currentFrameIndex)) {
      return new Shot[0];
    }

    Frame nextFrame = nextFrame(currentFrameIndex);
    int validShotCount = nextFrame.allValidShots().length;
    if (validShotCount == 0) {
      return new Shot[0];
    }

    if (validShotCount == 2 || validShotCount == 3) {
      return nextFrame.firstTwoShots();
    }
    Shot shot = nextShot(currentFrameIndex + 1);
    return shot.isValid() ?
      new Shot[]{nextFrame.getFirstShot(), shot} : new Shot[]{nextFrame.getFirstShot()};
  }

  private Frame nextFrame(int index) {
    return frames[index + 1];
  }

  private boolean nextFrameExist(int index) {
    return index + 1 < 10;
  }

  public Shot nextShot(int frameIndex) {
    return nextFrameExist(frameIndex) ? nextFrame(frameIndex).getFirstShot() : Shot.NO_SHOT;
  }
}
