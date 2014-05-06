package bowling;

/**
 * User: lantao yan
 * Date: 4/30/14
 */
public class Player {
  private final String name;

  public Player(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Player{" +
      "name='" + name + '\'' +
      '}';
  }
}
