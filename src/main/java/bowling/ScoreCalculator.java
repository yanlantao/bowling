package bowling;

/**
 * User: lantao.yan
 * Date: 30/04/2014
 */
public class ScoreCalculator {
  private final FrameController frameController;

  public ScoreCalculator(FrameController frameController) {
    this.frameController = frameController;
  }

  public int calculate() {
    return calculateFirstNineFrames() + tenthFrame();
  }

  private int tenthFrame() {
    return frameController.getFrame(9).totalScore();
  }

  private int calculateFirstNineFrames() {
    int result = 0;
    for (int i = 0; i < frameController.totalFrameCount() - 1; i++) {
      Frame currentFrame = frameController.getFrame(i);
      result += currentFrame.totalScore();
      if (currentFrame.isStrike()) {
        result += scoreOf(frameController.nextTwoShots(i));
      } else if (currentFrame.isSpare()) {
        result += scoreOf(frameController.nextShot(i));
      }
    }
    return result;
  }

  private int scoreOf(Shot... shots) {
    int result = 0;
    for (Shot eachShot : shots) {
      result += eachShot.score();
    }
    return result;
  }
}
