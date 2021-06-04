import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SuitType;
import org.junit.Test;

/**
 * This class extends the abstract ModelTest class and contains tests that are specific to the
 * SimpleFreecellModel model class.
 */
public class SimpleFreecellModelExtendedTest extends ModelTest {


  @Override
  protected FreecellModel creator() {
    return FreecellModelCreator.create(GameType.SINGLEMOVE);
  }


  @Test
  public void testGetOpenCardAt1() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    assertEquals(new Card(SuitType.HEARTS, CardRank.ACE), model.getOpenCardAt(1));
  }


  @Test
  public void testGetFoundationCardAt00() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.ACE),
        model.getFoundationCardAt(0, 0));
  }

  @Test
  public void testGetFoundationCardAt01() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.TWO),
        model.getFoundationCardAt(0, 1));
  }

  @Test
  public void testGetFoundationCardAt012() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.KING),
        model.getFoundationCardAt(0, 12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionWrongCardIndex() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    model.getFoundationCardAt(0, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionWrongPileIndex() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    model.getFoundationCardAt(5, 12);
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveNotStarted() {
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNonAceCardFromCascadeToEmptyFoundation() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTwoCardsToAnOpenPileFromCacade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameColorCardsToACasCadePileFromCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongSuitToFoundationPileFromCacade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongValueSameSuitToFoundationPileFromCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongSuitToFoundationPileFromOpen() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 5, 0, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameColorCardsToACasCadePileFromOpen() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromOPEN() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromCascadeEMPTY() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoFULLCascadePileFromOPEN() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveGreaterRanktoCascadePileFromOPEN() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 8, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen0() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen1() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 1, 0, PileType.OPEN, 1);
    model.getCascadeCardAt(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen2() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 2, 0, PileType.OPEN, 2);
    model.getCascadeCardAt(2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen3() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 3, 0, PileType.OPEN, 3);
    model.getCascadeCardAt(3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToFoundation3() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 3);
    model.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToFoundation0() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.getCascadeCardAt(0, 0);
  }

  @Test
  public void testIsGameOver() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 0);

    model.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 1);

    model.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 2);

    model.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void testSimpleModel() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    try {
      model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("the provided card to be moved is not the last card in the source pile",
          e.getMessage());
    }
  }

}
