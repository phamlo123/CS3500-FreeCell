import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a mock model for the SimpleFreecellModel to help test the methods implemented
 * in the SimpleFreecellController class.
 */
public class MockModel implements FreecellModel {

  private final StringBuilder log;
  private final StringBuilder irrelevantLog;

  /**
   * Create a mock model object for this class.
   *
   * @param log           is a String Builder that will be appended String into for testing
   *                      purposes
   * @param irrelevantLog is an irrelevant String Builder for methods that do not appear in the
   *                      controller class.
   */
  public MockModel(StringBuilder log, StringBuilder irrelevantLog) {
    this.log = log;
    this.irrelevantLog = irrelevantLog;
  }


  @Override
  public List getDeck() {
    log.append("getDeck was called correctly.");
    return new ArrayList();
  }

  @Override
  public void startGame(List deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    log.append(String.format(" Provided deck with %d cascade piles and "
            + "%d open piles and shuffle status is %b.", numCascadePiles,
        numOpenPiles,
        shuffle));
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    log.append(String.format(" Source pile type is %s, "
            + "source pile number is %d, card index is %d, destination pile type is"
            + " %s, destination pile number is %d.", source, pileNumber, cardIndex, destination,
        destPileNumber));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getNumCardsInFoundationPile is called");
    return 0;
  }

  @Override
  public int getNumCascadePiles() {
    irrelevantLog.append("getNumCascadePiles is called");
    return 0;
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getNumCardsInCascadePile is called");
    return 0;
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getNumCardsInOpenPile is called");

    return 0;
  }

  @Override
  public int getNumOpenPiles() {
    irrelevantLog.append("getNumOpenPiles is called");

    return 0;
  }

  @Override
  public Object getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getFoundationCardAt is called");

    return null;
  }

  @Override
  public Object getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getCascadeCardAt is called");

    return null;
  }

  @Override
  public Object getOpenCardAt(int pileIndex)
      throws IllegalArgumentException, IllegalStateException {
    irrelevantLog.append("getOpenCardAt is called");

    return null;
  }
}