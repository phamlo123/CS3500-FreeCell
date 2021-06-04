import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;


import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.FreecellModelState;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.SuitType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Abstract class that contains tests for functionalities that both SimpleFreecellModel and
 * ComplexFreecellModel implements.
 */
public abstract class ModelTest {


  /**
   * Create a FreecellModel for testing.
   *
   * @return a FreecellModel based on specific class implementation
   */
  protected abstract FreecellModel creator();

  FreecellModel model = creator();
  List<Card> deck = creator().getDeck();


  @Test
  public void testDeckSize() {
    assertEquals(52, deck.size());
  }

  @Test
  public void testDeckCountSpades() {
    assertEquals(13, countSuits(deck, SuitType.CLUBS));
  }

  @Test
  public void testDeckCountHearts() {
    assertEquals(13, countSuits(deck, SuitType.HEARTS));
  }

  @Test
  public void testDeckCountClubs() {
    assertEquals(13, countSuits(deck, SuitType.CLUBS));
  }

  @Test
  public void testDeckCountDiamonds() {
    assertEquals(13, countSuits(deck, SuitType.DIAMONDS));
  }

  @Test
  public void testDeckCountAce() {
    assertEquals(4, countRanks(deck, CardRank.ACE));
  }

  @Test
  public void testDeckCountTwo() {
    assertEquals(4, countRanks(deck, CardRank.TWO));
  }

  @Test
  public void testDeckCountThree() {
    assertEquals(4, countRanks(deck, CardRank.THREE));
  }

  @Test
  public void testDeckCountFOUR() {
    assertEquals(4, countRanks(deck, CardRank.FOUR));
  }

  @Test
  public void testDeckCountFive() {
    assertEquals(4, countRanks(deck, CardRank.FIVE));
  }

  @Test
  public void testDeckCountSix() {
    assertEquals(4, countRanks(deck, CardRank.SIX));
  }

  @Test
  public void testDeckCountSeven() {
    assertEquals(4, countRanks(deck, CardRank.SEVEN));
  }

  @Test
  public void testDeckCountEight() {
    assertEquals(4, countRanks(deck, CardRank.EIGHT));
  }

  @Test
  public void testDeckCountNine() {
    assertEquals(4, countRanks(deck, CardRank.NINE));
  }

  @Test
  public void testDeckCountTen() {
    assertEquals(4, countRanks(deck, CardRank.TEN));
  }

  @Test
  public void testDeckCountJACK() {
    assertEquals(4, countRanks(deck, CardRank.JACK));
  }

  @Test
  public void testDeckCountQueen() {
    assertEquals(4, countRanks(deck, CardRank.QUEEN));
  }

  @Test
  public void testDeckCountKing() {
    assertEquals(4, countRanks(deck, CardRank.KING));
  }

  @Test
  public void checkIdenticalItems() {
    assertEquals(false, checkIdentical(deck));
  }


  FreecellModelState<Card> blankModel = new SimpleFreecellModel();
  FreecellModel<Card> modelTest = new SimpleFreecellModel();


  @Test
  public void testNumOfOpenPiles() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(4, modelTest.getNumOpenPiles());
  }

  @Test
  public void testNumOfOpenPiles2() {
    modelTest.startGame(deck, 8, 5, false);
    assertEquals(5, modelTest.getNumOpenPiles());
  }

  @Test
  public void testNumOfOpenPilesNotStarted() {
    assertEquals(-1, modelTest.getNumOpenPiles());
  }

  @Test
  public void testGetNumCasCadePileNotStarted() {
    assertEquals(-1, modelTest.getNumCascadePiles());
  }

  @Test
  public void testGetNumCascadePile() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(8, modelTest.getNumCascadePiles());
  }

  @Test
  public void testGetNumCascadePile2() {
    modelTest.startGame(deck, 4, 1, true);
    assertEquals(4, modelTest.getNumCascadePiles());
  }


  @Test
  public void testNumOpenPilesNotStart() {
    assertEquals(-1, blankModel.getNumOpenPiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardOpenPileWrongIndex2() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getOpenCardAt(-1);
  }

  @Test
  public void testGetNumOfCardOpenPiles() {
    modelTest.startGame(deck, 8, 4, true);
    assertEquals(0, modelTest.getNumCardsInOpenPile(0));
  }

  @Test
  public void testGetNumOfCardOpenPiles2() {
    modelTest.startGame(deck, 8, 4, true);
    assertEquals(0, modelTest.getNumCardsInOpenPile(1));
  }

  @Test
  public void testGetNumOfCardOpenPiles3() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(0, modelTest.getNumCardsInOpenPile(2));
  }

  @Test
  public void testGetNumOfCardOpenPiles4() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(0, modelTest.getNumCardsInOpenPile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardOpenPileWrongIndex() {
    modelTest.startGame(deck, 8, 4, true);
    modelTest.getNumCardsInOpenPile(10);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumOfCardOpenPileNotStarted() {
    modelTest.getNumCardsInOpenPile(0);
  }

  @Test
  public void testNumCascadePiles() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(8, modelTest.getNumCascadePiles());
  }

  @Test
  public void testNumCascadePilesNotStarted() {
    assertEquals(-1, blankModel.getNumCascadePiles());
  }

  @Test
  public void testGetNumberOfCardAtCascadePile1() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(7, modelTest.getNumCardsInCascadePile(0));
  }

  @Test
  public void testGetNumberOfCardAtCascadePile8() {
    modelTest.startGame(deck, 8, 4, true);
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumberOfCardAtCascadePileNotStarted() {
    blankModel.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumberOfCardAtCascadeWrongIndex() {
    modelTest.startGame(deck, 8, 4, true);
    modelTest.getNumCardsInCascadePile(10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumberOfCardAtCascadeWrongIndex2() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getNumCardsInCascadePile(-1);
  }


  @Test
  public void testGetNumOfCardFoundationPile0() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGetNumOfCardFoundationPile1() {
    modelTest.startGame(deck, 8, 4, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(1));
  }

  @Test
  public void testGetNumOfCardFoundationPile2() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGetNumOfCardFoundationPile3() {
    modelTest.startGame(deck, 8, 4, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(3));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumOfCardFoundationPileNotStarted() {
    modelTest.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardFoundationWrongIndex1() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getNumCardsInFoundationPile(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardFoundationWrongIndex2() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getNumCardsInFoundationPile(4);
  }

  @Test
  public void testGetCardAtCascadePile06() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.KING),
        modelTest.getCascadeCardAt(0, 6));
  }

  @Test
  public void testGetCardAtCascadePile01() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.THREE),
        modelTest.getCascadeCardAt(0, 1));
  }

  @Test
  public void testGetCardAtCascadePile02() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.FIVE),
        modelTest.getCascadeCardAt(0, 2));
  }

  @Test
  public void testGetCardAtCascadePile55() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.DIAMONDS, CardRank.QUEEN),
        modelTest.getCascadeCardAt(5, 5));
  }

  @Test
  public void testGetCardAtCascadePile50() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.DIAMONDS, CardRank.TWO),
        modelTest.getCascadeCardAt(5, 0));
  }

  @Test
  public void testGetCardAtCascadePile44() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.TEN),
        modelTest.getCascadeCardAt(4, 4));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtCascadePileNotStarted() {
    assertEquals(new Card(SuitType.HEARTS, CardRank.TEN),
        modelTest.getCascadeCardAt(8, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexBoth() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getCascadeCardAt(10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexPile() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getCascadeCardAt(10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexCard() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getCascadeCardAt(0, 7);
  }


  @Test
  public void testStartGameRoundRobin00() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(0), modelTest.getCascadeCardAt(0, 0));
  }

  @Test
  public void testStartGameRoundRobin01() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(8), modelTest.getCascadeCardAt(0, 1));
  }

  @Test
  public void testStartGameRoundRobin02() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(16), modelTest.getCascadeCardAt(0, 2));
  }

  @Test
  public void testStartGameRoundRobin03() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(24), modelTest.getCascadeCardAt(0, 3));
  }

  @Test
  public void testStartGameRoundRobin04() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(32), modelTest.getCascadeCardAt(0, 4));
  }

  @Test
  public void testStartGameRoundRobin05() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(40), modelTest.getCascadeCardAt(0, 5));
  }

  @Test
  public void testStartGameRoundRobin06() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(48), modelTest.getCascadeCardAt(0, 6));
  }

  @Test
  public void testStartGameRoundRobin10() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(1), modelTest.getCascadeCardAt(1, 0));
  }

  @Test
  public void testStartGameRoundRobin20() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(2), modelTest.getCascadeCardAt(2, 0));
  }


  @Test
  public void testStartGameRoundRobin30() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(3), modelTest.getCascadeCardAt(3, 0));
  }

  @Test
  public void testStartGameRoundRobin40() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(4), modelTest.getCascadeCardAt(4, 0));
  }

  @Test
  public void testStartGameRoundRobin50() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(5), modelTest.getCascadeCardAt(5, 0));
  }

  @Test
  public void testStartGameRoundRobin60() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(6), modelTest.getCascadeCardAt(6, 0));
  }

  @Test
  public void testStartGameRoundRobin70() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(7), modelTest.getCascadeCardAt(7, 0));
  }


  @Test
  public void testStartGameRoundRobin16() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(49), modelTest.getCascadeCardAt(1, 6));
  }

  @Test
  public void testStartGameRoundRobin26() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(50), modelTest.getCascadeCardAt(2, 6));
  }

  @Test
  public void testStartGameRoundRobin36() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(deck.get(51), modelTest.getCascadeCardAt(3, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobin46() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getCascadeCardAt(4, 6);
  }

  @Test
  public void testStartGameRoundRobinGetNumCardsO() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(7, modelTest.getNumCardsInCascadePile(0));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards1() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(7, modelTest.getNumCardsInCascadePile(1));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards2() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(7, modelTest.getNumCardsInCascadePile(2));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards3() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(7, modelTest.getNumCardsInCascadePile(3));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards4() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(6, modelTest.getNumCardsInCascadePile(4));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards5() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(6, modelTest.getNumCardsInCascadePile(5));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards6() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(6, modelTest.getNumCardsInCascadePile(6));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards7() {
    modelTest.startGame(deck, 8, 4, false);
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobinGetNumCards8() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getNumCardsInCascadePile(8);
  }


  @Test
  public void testStartGameRoundRobinNoRemainder00() {
    modelTest.startGame(deck, 13, 4, false);
    assertEquals(deck.get(0), modelTest.getCascadeCardAt(0, 0));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder01() {
    modelTest.startGame(deck, 13, 4, false);
    assertEquals(deck.get(13), modelTest.getCascadeCardAt(0, 1));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder02() {
    modelTest.startGame(deck, 13, 4, false);
    assertEquals(deck.get(26), modelTest.getCascadeCardAt(0, 2));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder03() {
    modelTest.startGame(deck, 13, 4, false);
    assertEquals(deck.get(39), modelTest.getCascadeCardAt(0, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobinNoRemainder04() {
    modelTest.startGame(deck, 13, 4, false);
    modelTest.getCascadeCardAt(0, 4);
  }

  @Test
  public void testGameStartShuffle00() {
    modelTest.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(false, modelTest.getCascadeCardAt(0, 0)
        .equals(model.getCascadeCardAt(0, 0)));
  }


  @Test
  public void testGameStartShuffle01() {
    modelTest.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(false, modelTest.getCascadeCardAt(1, 0)
        .equals(model.getCascadeCardAt(1, 0)));
  }

  @Test
  public void testGameStartShuffle02() {
    modelTest.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(false, modelTest.getCascadeCardAt(3, 0)
        .equals(model.getCascadeCardAt(3, 0)));
  }

  @Test
  public void testGameStartShuffle03() {
    modelTest.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(false, modelTest.getCascadeCardAt(4, 0)
        .equals(model.getCascadeCardAt(4, 0)));
  }

  @Test
  public void testGameStartShuffleCascadePile0() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(0));
  }


  @Test
  public void testGameStartShuffleCascadePile1() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(1));
  }

  @Test
  public void testGameStartShuffleCascadePile2() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(2));
  }

  @Test
  public void testGameStartShuffleCascadePile3() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(3));
  }

  @Test
  public void testGameStartShuffleCascadePile4() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(4));
  }

  @Test
  public void testGameStartShuffleCascadePile5() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(5));
  }

  @Test
  public void testGameStartShuffleCascadePile6() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(6));
  }

  @Test
  public void testGameStartShuffleCascadePile7() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(7));
  }

  @Test
  public void testGameStartShuffleCascadePile8() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(8));
  }

  @Test
  public void testGameStartShuffleCascadePile9() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(9));
  }

  @Test
  public void testGameStartShuffleCascadePile10() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(10));
  }

  @Test
  public void testGameStartShuffleCascadePil11() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(11));
  }

  @Test
  public void testGameStartShuffleCascadePile12() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStartShuffleCascadePile13() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(4, modelTest.getNumCardsInCascadePile(13));
  }

  @Test
  public void testGameStartShuffleFoundationPiles0() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGameStartShuffleFoundationPiles1() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(1));
  }

  @Test
  public void testGameStartShuffleFoundationPiles2() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGameStartShuffleFoundationPiles3() {
    modelTest.startGame(deck, 13, 3, true);
    assertEquals(0, modelTest.getNumCardsInFoundationPile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStartShuffleFoundationPiles4() {
    modelTest.startGame(deck, 13, 3, true);
    modelTest.getNumCardsInFoundationPile(4);
  }


  @Test
  public void testStartGameGetNumOpenPiles() {
    modelTest.startGame(deck, 13, 4, true);
    assertEquals(4, modelTest.getNumOpenPiles());
  }

  @Test
  public void testStartGameGetNumOpenPiles2() {
    modelTest.startGame(deck, 13, 5, false);
    assertEquals(5, modelTest.getNumOpenPiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameGetNumOpenPilesException() {
    modelTest.startGame(deck, 13, 0, true);
  }

  @Test
  public void testGetOpenCardEmpty() {
    modelTest.startGame(deck, 13, 3, false);
    assertEquals(null, modelTest.getOpenCardAt(2));
  }


  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtNotStart() {
    modelTest.getOpenCardAt(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWrongDeck() {
    deck.add(new Card(SuitType.HEARTS, CardRank.ACE));
    modelTest.startGame(deck, 13, 3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardEmptyException() {
    modelTest.startGame(deck, 8, 4, false);
    modelTest.getFoundationCardAt(0, 0);
  }


  @Test
  public void testIsGameOverFalse() {
    modelTest.startGame(deck, 52, 4, false);
    assertEquals(false, modelTest.isGameOver());
  }

  @Test
  public void testIsGameOverNotStarted() {
    assertEquals(false, modelTest.isGameOver());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectDeck2() {
    deck.remove(0);
    model.startGame(deck, 13, 2, false);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectCascadeStartGame() {
    model.startGame(model.getDeck(), 3, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectOpenStartGame() {
    model.startGame(model.getDeck(), 4, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectDeck() {
    List<Card> a = new ArrayList<>();
    a.add(new Card(SuitType.HEARTS, CardRank.TEN));
    a.add(new Card(SuitType.HEARTS, CardRank.TWO));
    model.startGame(a, 13, 2, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectDeck3() {
    deck.remove(0);
    model.startGame(deck, 13, 2, false);
  }


  @Test
  public void testGetOpenCardAt() {
    model.startGame(deck, 52, 2, false);
    assertEquals(null, model.getOpenCardAt(0));
    model.move(PileType.CASCADE, 4, 0, PileType.OPEN, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.TWO), model.getOpenCardAt(0));
    assertEquals(null, model.getOpenCardAt(1));

    FreecellModel model2 = FreecellModelCreator.create(GameType.MULTIMOVE);
    try {
      model2.getOpenCardAt(0);
    } catch (IllegalStateException e) {
      assertEquals("The game has not started", e.getMessage());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAt2() {
    model.startGame(deck, 52, 2, false);
    model.getOpenCardAt(-1);
  }

  @Test
  public void testGetCascadeCardAt() {
    model.startGame(deck, 52, 2, false);
    for (int i = 0; i < 52; i++) {
      assertEquals(deck.get(i),
          model.getCascadeCardAt(i, 0));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAt3() {
    model.startGame(deck, 52, 2, false);
    model.getCascadeCardAt(55, 0);
  }

  @Test
  public void testGetCascadeCardAt4() {
    try {
      model.getCascadeCardAt(0, 0);
    } catch (IllegalStateException e) {
      assertEquals("The game has not started", e.getMessage());
    }
  }

  @Test
  public void testGetCascadeCard5() {
    model.startGame(deck, 52, 2, false);
    try {
      model.getCascadeCardAt(0, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("The provided Cascade card index is not valid", e.getMessage());
    }
  }

  //the methods below are used for the purpose of assisting testing.

  private int countSuits(List<Card> deck, SuitType s) {
    int count = 0;
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i).getSuit() == s) {
        count++;
      }
    }
    return count;
  }

  private int countRanks(List<Card> deck, CardRank c) {
    int count = 0;
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i).getRank() == c) {
        count++;
      }
    }
    return count;
  }

  //Check if there is any two identical cards in a given list of Cards.
  private boolean checkIdentical(List<Card> deck) {
    for (int i = 0; i < deck.size() - 1; i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals(deck.get(j))) {
          return true;
        }
      }
    }
    return false;
  }
}