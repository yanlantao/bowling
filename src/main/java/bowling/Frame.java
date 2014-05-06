package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * User: lantao.yan
 * Date: 30/04/2014
 */
public class Frame {
  private List<Shot> allShots;

  public Frame() {
    allShots = new ArrayList<Shot>();
  }

  public int totalScore() {
    int result = 0;
    for (Shot eachShot : allShots) {
      result += eachShot.score();
    }
    return result;
  }

  public boolean isStrike() {
    return allShots.size() == 1 && allShots.get(0).score() == 10;
  }

  public boolean isSpare() {
    return allShots.size() == 2 && !isStrike() && (allShots.get(0).score() + allShots.get(1).score() == 10);
  }

  public Shot[] allValidShots() {
    return allShots.toArray(new Shot[0]);
  }

  public void addShot(Shot shot) {
    allShots.add(shot);
  }

  public Shot getFirstShot() {
    return allShots.size() > 0 ? allShots.get(0) : Shot.NO_SHOT;
  }

  public boolean hasTwoShots() {
    return allShots.size() == 2;
  }

  public Shot[] firstTwoShots() {
    return new Shot[]{allShots.get(0), allShots.get(1)};
  }

  public boolean isNonBonus() {
    return totalScore() < 10;
  }
}
