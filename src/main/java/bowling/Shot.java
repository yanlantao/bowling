package bowling;

/**
 * User: lantao.yan
 * Date: 30/04/2014
 */
public class Shot {

  public static final Shot NO_SHOT = new Shot(-1);
  private final int score;

  public Shot(int score) {
    this.score = score;
  }

  public int score() {
    return score == -1 ? 0 : score;
  }

  public boolean isValid() {
    return score != -1;
  }
}
