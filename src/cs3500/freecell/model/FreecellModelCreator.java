package cs3500.freecell.model;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.ComplexFreecellModel;

/**
 * This is a factory class contains method for creating a Freecell game model.
 */
public class FreecellModelCreator {

  /**
   * Type for the different models of the game Freecell. SINGLEMOVE model do not support multi-card
   * move, while MULTIMODEL does support multi-card move.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Create a Freecell model according to the GameType input.
   *
   * @param gameType gameType of the model to be created
   * @return the model of the game Freecell
   * @throws IllegalArgumentException if the gameType is not supported
   */
  public static FreecellModel create(GameType gameType) throws IllegalArgumentException {
    if (gameType == GameType.SINGLEMOVE) {
      return new SimpleFreecellModel();
    } else if (gameType == GameType.MULTIMOVE) {
      return new ComplexFreecellModel();
    } else {
      throw new IllegalArgumentException("Unsupported model type.");
    }
  }

}
