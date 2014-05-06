package bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Rules of ten-pin bowling
 * A game of bowling consists of ten frames. In each frame, the bowler will have two chances to knock down as many pins as possible with his bowling ball.
 * In games with more than one bowler, every bowler will take his frame in a predetermined order before the next frame begins.
 * In general, one point is scored for each pin that is knocked over. So if a player bowls over three pins with the first shot,
 * then six with the second, the player would receive a total of nine points for that frame. If a player knocks down 9 pins with the first shot,
 * but misses with the second, the player would also score nine. In the event that all ten pins are knocked over by a player in a single frame,
 * bonuses are awarded. If a bowler is able to knock down all ten pins with the first ball, it is known as a strike.
 * If the bowler is able to knock down all 10 pins with the two balls of a frame, it is known as a spare.
 * Bonus points are awarded for both of these, depending on what is scored in the next 2 balls (for a strike) or 1 ball (for a spare).
 * If the bowler knocks down all 10 pins in the tenth frame, the bowler is allowed to throw 3 balls for that frame.
 * This allows for a potential of 12 strikes in a single game, and a maximum score of 300 points, a perfect game.
 *
 * Bonus scoring: Strikes
 * When all ten pins are knocked down with the first ball (called a strike and typically rendered as an "X" on a scoresheet),
 * a player is awarded ten points, plus a bonus of whatever is scored with the next two balls. In this way,
 * the points scored for the two balls after the strike are counted twice.
 * Example:
 * Frame 1, ball 1: 10 pins (strike)
 * Frame 2, ball 1: 3 pins
 * Frame 2, ball 2: 6 pins
 * The total score from these throws is:
 * Frame one: 10 + (3 + 6) = 19
 * Frame two: 3 + 6 = 9
 * TOTAL = 28
 * A player who bowls a strike in the tenth (final) frame is awarded one extra ball to allow for the bonus points.
 *
 * Bonus scoring: Spares
 * A "spare" is awarded when no pins are left standing after the second ball of a frame; i.e., a player uses both balls of a frame to clear all ten pins.
 * A player achieving a spare is awarded ten points, plus a bonus of whatever is scored with the next ball (only the first ball is counted).
 * It is typically rendered as a slash on scoresheets in place of the second pin count for a frame.
 * Example:
 * Frame 1, ball 1: 7 pins
 * Frame 1, ball 2: 3 pins (spare)
 * Frame 2, ball 1: 4 pins
 * Frame 2, ball 2: 2 pins
 * The total score from these throws is:
 * Frame one: 7 + 3 + 4 (bonus) = 14
 * Frame two: 4 + 2 = 6
 * TOTAL = 20
 * A player who bowls a spare in the tenth (final) frame is awarded one extra ball to allow for the bonus points.
 */
public class TenPinBowling {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("It's bowling game, how many players?");
    // ignore the arg validation
    int playerCount = Integer.parseInt(br.readLine());
    Player[] allPlayers = new Player[playerCount];
    for (int i=0; i< playerCount; i++) {
      System.out.println("please enter player" + i + "'s name: " );
      allPlayers[i] = new Player(br.readLine());
    }
    Game game = Game.startGame(allPlayers);

    while (!game.isOver()) {
      System.out.println(game.currentPlayer() + "'s current score is: " + game.scoreForCurrentPlayer());
      System.out.println("Frame " + game.currentFrameIndex() + " - Please enter score for " + game.currentPlayer() + " : ");
      int score = Integer.parseInt(br.readLine());
      game.submitScore(new Score(score));
    }
    System.out.println("---------- Final results ----------");
    System.out.println(game.finalResult());
    System.out.println("Game finish...");
  }
}
