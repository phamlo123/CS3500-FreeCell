import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;


import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
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

  


  @Test
  public void testNumOfOpenPiles() {
    model.startGame(deck, 8, 4, false);
    assertEquals(4, model.getNumOpenPiles());
  }

  @Test
  public void testNumOfOpenPiles2() {
    model.startGame(deck, 8, 5, false);
    assertEquals(5, model.getNumOpenPiles());
  }

  @Test
  public void testNumOfOpenPilesNotStarted() {
    assertEquals(-1, model.getNumOpenPiles());
  }

  @Test
  public void testGetNumCasCadePileNotStarted() {
    assertEquals(-1, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumCascadePile() {
    model.startGame(deck, 8, 4, false);
    assertEquals(8, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumCascadePile2() {
    model.startGame(deck, 4, 1, true);
    assertEquals(4, model.getNumCascadePiles());
  }


  @Test
  public void testNumOpenPilesNotStart() {
    assertEquals(-1, model.getNumOpenPiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardOpenPileWrongIndex2() {
    model.startGame(deck, 8, 4, false);
    model.getOpenCardAt(-1);
  }

  @Test
  public void testGetNumOfCardOpenPiles() {
    model.startGame(deck, 8, 4, true);
    assertEquals(0, model.getNumCardsInOpenPile(0));
  }

  @Test
  public void testGetNumOfCardOpenPiles2() {
    model.startGame(deck, 8, 4, true);
    assertEquals(0, model.getNumCardsInOpenPile(1));
  }

  @Test
  public void testGetNumOfCardOpenPiles3() {
    model.startGame(deck, 8, 4, false);
    assertEquals(0, model.getNumCardsInOpenPile(2));
  }

  @Test
  public void testGetNumOfCardOpenPiles4() {
    model.startGame(deck, 8, 4, false);
    assertEquals(0, model.getNumCardsInOpenPile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardOpenPileWrongIndex() {
    model.startGame(deck, 8, 4, true);
    model.getNumCardsInOpenPile(10);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumOfCardOpenPileNotStarted() {
    model.getNumCardsInOpenPile(0);
  }

  @Test
  public void testNumCascadePiles() {
    model.startGame(deck, 8, 4, false);
    assertEquals(8, model.getNumCascadePiles());
  }

  @Test
  public void testNumCascadePilesNotStarted() {
    assertEquals(-1, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumberOfCardAtCascadePile1() {
    model.startGame(deck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void testGetNumberOfCardAtCascadePile8() {
    model.startGame(deck, 8, 4, true);
    assertEquals(6, model.getNumCardsInCascadePile(7));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumberOfCardAtCascadePileNotStarted() {
    model.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumberOfCardAtCascadeWrongIndex() {
    model.startGame(deck, 8, 4, true);
    model.getNumCardsInCascadePile(10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumberOfCardAtCascadeWrongIndex2() {
    model.startGame(deck, 8, 4, false);
    model.getNumCardsInCascadePile(-1);
  }


  @Test
  public void testGetNumOfCardFoundationPile0() {
    model.startGame(deck, 8, 4, false);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGetNumOfCardFoundationPile1() {
    model.startGame(deck, 8, 4, true);
    assertEquals(0, model.getNumCardsInFoundationPile(1));
  }

  @Test
  public void testGetNumOfCardFoundationPile2() {
    model.startGame(deck, 8, 4, false);
    assertEquals(0, model.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGetNumOfCardFoundationPile3() {
    model.startGame(deck, 8, 4, true);
    assertEquals(0, model.getNumCardsInFoundationPile(3));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumOfCardFoundationPileNotStarted() {
    model.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardFoundationWrongIndex1() {
    model.startGame(deck, 8, 4, false);
    model.getNumCardsInFoundationPile(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumOfCardFoundationWrongIndex2() {
    model.startGame(deck, 8, 4, false);
    model.getNumCardsInFoundationPile(4);
  }

  @Test
  public void testGetCardAtCascadePile06() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.KING),
        model.getCascadeCardAt(0, 6));
  }

  @Test
  public void testGetCardAtCascadePile01() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.THREE),
        model.getCascadeCardAt(0, 1));
  }

  @Test
  public void testGetCardAtCascadePile02() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.FIVE),
        model.getCascadeCardAt(0, 2));
  }

  @Test
  public void testGetCardAtCascadePile55() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.DIAMONDS, CardRank.QUEEN),
        model.getCascadeCardAt(5, 5));
  }

  @Test
  public void testGetCardAtCascadePile50() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.DIAMONDS, CardRank.TWO),
        model.getCascadeCardAt(5, 0));
  }

  @Test
  public void testGetCardAtCascadePile44() {
    model.startGame(deck, 8, 4, false);
    assertEquals(new Card(SuitType.HEARTS, CardRank.TEN),
        model.getCascadeCardAt(4, 4));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtCascadePileNotStarted() {
    assertEquals(new Card(SuitType.HEARTS, CardRank.TEN),
        model.getCascadeCardAt(8, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexBoth() {
    model.startGame(deck, 8, 4, false);
    model.getCascadeCardAt(10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexPile() {
    model.startGame(deck, 8, 4, false);
    model.getCascadeCardAt(10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadePileWrongIndexCard() {
    model.startGame(deck, 8, 4, false);
    model.getCascadeCardAt(0, 7);
  }


  @Test
  public void testStartGameRoundRobin00() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(0), model.getCascadeCardAt(0, 0));
  }

  @Test
  public void testStartGameRoundRobin01() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(8), model.getCascadeCardAt(0, 1));
  }

  @Test
  public void testStartGameRoundRobin02() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(16), model.getCascadeCardAt(0, 2));
  }

  @Test
  public void testStartGameRoundRobin03() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(24), model.getCascadeCardAt(0, 3));
  }

  @Test
  public void testStartGameRoundRobin04() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(32), model.getCascadeCardAt(0, 4));
  }

  @Test
  public void testStartGameRoundRobin05() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(40), model.getCascadeCardAt(0, 5));
  }

  @Test
  public void testStartGameRoundRobin06() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(48), model.getCascadeCardAt(0, 6));
  }

  @Test
  public void testStartGameRoundRobin10() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(1), model.getCascadeCardAt(1, 0));
  }

  @Test
  public void testStartGameRoundRobin20() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(2), model.getCascadeCardAt(2, 0));
  }


  @Test
  public void testStartGameRoundRobin30() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(3), model.getCascadeCardAt(3, 0));
  }

  @Test
  public void testStartGameRoundRobin40() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(4), model.getCascadeCardAt(4, 0));
  }

  @Test
  public void testStartGameRoundRobin50() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(5), model.getCascadeCardAt(5, 0));
  }

  @Test
  public void testStartGameRoundRobin60() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(6), model.getCascadeCardAt(6, 0));
  }

  @Test
  public void testStartGameRoundRobin70() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(7), model.getCascadeCardAt(7, 0));
  }


  @Test
  public void testStartGameRoundRobin16() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(49), model.getCascadeCardAt(1, 6));
  }

  @Test
  public void testStartGameRoundRobin26() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(50), model.getCascadeCardAt(2, 6));
  }

  @Test
  public void testStartGameRoundRobin36() {
    model.startGame(deck, 8, 4, false);
    assertEquals(deck.get(51), model.getCascadeCardAt(3, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobin46() {
    model.startGame(deck, 8, 4, false);
    model.getCascadeCardAt(4, 6);
  }

  @Test
  public void testStartGameRoundRobinGetNumCardsO() {
    model.startGame(deck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards1() {
    model.startGame(deck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(1));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards2() {
    model.startGame(deck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(2));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards3() {
    model.startGame(deck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(3));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards4() {
    model.startGame(deck, 8, 4, false);
    assertEquals(6, model.getNumCardsInCascadePile(4));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards5() {
    model.startGame(deck, 8, 4, false);
    assertEquals(6, model.getNumCardsInCascadePile(5));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards6() {
    model.startGame(deck, 8, 4, false);
    assertEquals(6, model.getNumCardsInCascadePile(6));
  }

  @Test
  public void testStartGameRoundRobinGetNumCards7() {
    model.startGame(deck, 8, 4, false);
    assertEquals(6, model.getNumCardsInCascadePile(7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobinGetNumCards8() {
    model.startGame(deck, 8, 4, false);
    model.getNumCardsInCascadePile(8);
  }


  @Test
  public void testStartGameRoundRobinNoRemainder00() {
    model.startGame(deck, 13, 4, false);
    assertEquals(deck.get(0), model.getCascadeCardAt(0, 0));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder01() {
    model.startGame(deck, 13, 4, false);
    assertEquals(deck.get(13), model.getCascadeCardAt(0, 1));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder02() {
    model.startGame(deck, 13, 4, false);
    assertEquals(deck.get(26), model.getCascadeCardAt(0, 2));
  }

  @Test
  public void testStartGameRoundRobinNoRemainder03() {
    model.startGame(deck, 13, 4, false);
    assertEquals(deck.get(39), model.getCascadeCardAt(0, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRoundRobinNoRemainder04() {
    model.startGame(deck, 13, 4, false);
    model.getCascadeCardAt(0, 4);
  }

  @Test
  public void testGameStartShuffle00() {
    model.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(true, model.getCascadeCardAt(0, 0)
        .equals(model.getCascadeCardAt(0, 0)));
  }


  @Test
  public void testGameStartShuffle02() {
    model.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(true, model.getCascadeCardAt(3, 0)
        .equals(model.getCascadeCardAt(3, 0)));
  }

  @Test
  public void testGameStartShuffle03() {
    model.startGame(deck, 13, 4, true);
    model.startGame(deck, 13, 4, false);
    assertEquals(true, model.getCascadeCardAt(4, 0)
        .equals(model.getCascadeCardAt(4, 0)));
  }

  @Test
  public void testGameStartShuffleCascadePile0() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(0));
  }


  @Test
  public void testGameStartShuffleCascadePile1() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(1));
  }

  @Test
  public void testGameStartShuffleCascadePile2() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(2));
  }

  @Test
  public void testGameStartShuffleCascadePile3() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(3));
  }

  @Test
  public void testGameStartShuffleCascadePile4() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(4));
  }

  @Test
  public void testGameStartShuffleCascadePile5() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(5));
  }

  @Test
  public void testGameStartShuffleCascadePile6() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(6));
  }

  @Test
  public void testGameStartShuffleCascadePile7() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(7));
  }

  @Test
  public void testGameStartShuffleCascadePile8() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(8));
  }

  @Test
  public void testGameStartShuffleCascadePile9() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(9));
  }

  @Test
  public void testGameStartShuffleCascadePile10() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(10));
  }

  @Test
  public void testGameStartShuffleCascadePil11() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(11));
  }

  @Test
  public void testGameStartShuffleCascadePile12() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStartShuffleCascadePile13() {
    model.startGame(deck, 13, 3, true);
    assertEquals(4, model.getNumCardsInCascadePile(13));
  }

  @Test
  public void testGameStartShuffleFoundationPiles0() {
    model.startGame(deck, 13, 3, true);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGameStartShuffleFoundationPiles1() {
    model.startGame(deck, 13, 3, true);
    assertEquals(0, model.getNumCardsInFoundationPile(1));
  }

  @Test
  public void testGameStartShuffleFoundationPiles2() {
    model.startGame(deck, 13, 3, true);
    assertEquals(0, model.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGameStartShuffleFoundationPiles3() {
    model.startGame(deck, 13, 3, true);
    assertEquals(0, model.getNumCardsInFoundationPile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStartShuffleFoundationPiles4() {
    model.startGame(deck, 13, 3, true);
    model.getNumCardsInFoundationPile(4);
  }


  @Test
  public void testStartGameGetNumOpenPiles() {
    model.startGame(deck, 13, 4, true);
    assertEquals(4, model.getNumOpenPiles());
  }

  @Test
  public void testStartGameGetNumOpenPiles2() {
    model.startGame(deck, 13, 5, false);
    assertEquals(5, model.getNumOpenPiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameGetNumOpenPilesException() {
    model.startGame(deck, 13, 0, true);
  }

  @Test
  public void testGetOpenCardEmpty() {
    model.startGame(deck, 13, 3, false);
    assertEquals(null, model.getOpenCardAt(2));
  }


  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtNotStart() {
    model.getOpenCardAt(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWrongDeck() {
    deck.add(new Card(SuitType.HEARTS, CardRank.ACE));
    model.startGame(deck, 13, 3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardEmptyException() {
    model.startGame(deck, 8, 4, false);
    model.getFoundationCardAt(0, 0);
  }


  @Test
  public void testIsGameOverFalse() {
    model.startGame(deck, 52, 4, false);
    assertEquals(false, model.isGameOver());
  }

  @Test
  public void testIsGameOverNotStarted() {
    assertEquals(false, model.isGameOver());
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