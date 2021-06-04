import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelState;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.SuitType;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for SimpleFreecellModel class.
 */

public class SimpleFreecellModelTest {

  FreecellModel<Card> model = new SimpleFreecellModel();
  List<Card> deck = model.getDeck();


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

  @Test
  public void testGetOpenCardAt1() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    assertEquals(new Card(SuitType.HEARTS, CardRank.ACE), modelTest.getOpenCardAt(1));
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
  public void testGetFoundationCardAt00() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.ACE),
        modelTest.getFoundationCardAt(0, 0));
  }

  @Test
  public void testGetFoundationCardAt01() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.TWO),
        modelTest.getFoundationCardAt(0, 1));
  }

  @Test
  public void testGetFoundationCardAt012() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(SuitType.HEARTS, CardRank.KING),
        modelTest.getFoundationCardAt(0, 12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionWrongCardIndex() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    modelTest.getFoundationCardAt(0, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionWrongPileIndex() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    modelTest.getFoundationCardAt(5, 12);
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveNotStarted() {
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNonAceCardFromCascadeToEmptyFoundation() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTwoCardsToAnOpenPileFromCacade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameColorCardsToACasCadePileFromCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongSuitToFoundationPileFromCacade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongValueSameSuitToFoundationPileFromCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWrongSuitToFoundationPileFromOpen() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameColorCardsToACasCadePileFromOpen() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.OPEN, 1, 0, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromOPEN() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.OPEN, 1, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoCascadePileFromCascadeEMPTY() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRanktoFULLCascadePileFromOPEN() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveGreaterRanktoCascadePileFromOPEN() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.OPEN, 1);
    modelTest.move(PileType.OPEN, 1, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen0() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTest.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen1() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 1);
    modelTest.getCascadeCardAt(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen2() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 2, 0, PileType.OPEN, 2);
    modelTest.getCascadeCardAt(2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToOpen3() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 3, 0, PileType.OPEN, 3);
    modelTest.getCascadeCardAt(3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToFoundation3() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 3);
    modelTest.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToFoundation0() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.getCascadeCardAt(0, 0);
  }

  @Test
  public void testIsGameOver() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 0);

    modelTest.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 1);

    modelTest.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 2);

    modelTest.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    modelTest.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);
    assertEquals(true, modelTest.isGameOver());
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

  @Test
  public void testMoveFromCascadeToOpen08() {
    modelTest.startGame(deck, 13, 4, false);
    modelTest.move(PileType.CASCADE, 12, 3, PileType.OPEN, 0);
    assertEquals(3, modelTest.getNumCardsInCascadePile(12));
  }

  @Test
  public void testMoveFromCascadeToOpen09() {
    modelTest.startGame(deck, 13, 4, false);
    modelTest.move(PileType.CASCADE, 12, 3, PileType.OPEN, 0);
    assertEquals(1, modelTest.getNumCardsInOpenPile(0));
  }

  @Test
  public void testMoveFromCascadeToCascade() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToCascadeSameColor() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 2, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 2, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(4, modelTest.getNumCardsInCascadePile(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCascadeToCascadeValueNotOneAbove() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 1, 5, PileType.CASCADE, 7);
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior1() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(0, modelTest.getNumCardsInOpenPile(1));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior2() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(2));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior3() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(3));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior4() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(4));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior5() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(5));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior6() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(6));
  }

  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior7() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
  }


  @Test
  public void testMoveFromCascadeToCascadeSeeBehavior8() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 7);
    assertEquals(6, modelTest.getNumCardsInCascadePile(6));
    assertEquals(6, modelTest.getNumCardsInCascadePile(5));
    assertEquals(6, modelTest.getNumCardsInCascadePile(4));
    assertEquals(6, modelTest.getNumCardsInCascadePile(3));
    assertEquals(6, modelTest.getNumCardsInCascadePile(2));
    assertEquals(0, modelTest.getNumCardsInOpenPile(1));
    assertEquals(6, modelTest.getNumCardsInCascadePile(7));
    assertEquals(5, modelTest.getNumCardsInCascadePile(8));
    assertEquals(0, modelTest.getNumCardsInFoundationPile(0));
    assertEquals(0, modelTest.getNumCardsInFoundationPile(1));
    assertEquals(0, modelTest.getNumCardsInFoundationPile(2));
    assertEquals(0, modelTest.getNumCardsInFoundationPile(3));
    assertEquals(1, modelTest.getNumCardsInOpenPile(0));
    assertEquals(0, modelTest.getNumCardsInOpenPile(1));
    assertEquals(0, modelTest.getNumCardsInOpenPile(2));
    assertEquals(0, modelTest.getNumCardsInOpenPile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSameRankCasCadeToCascade() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 8);
  }

  @Test
  public void testMoveFromOpenToOpen() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals(0, modelTest.getNumCardsInOpenPile(0));
    assertEquals(1, modelTest.getNumCardsInOpenPile(1));
    assertEquals(5, modelTest.getNumCardsInCascadePile(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromOpenToFullOpen() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.CASCADE, 0, 4, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmptyOpentToOpen() {
    modelTest.startGame(deck, 9, 4, false);
    modelTest.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmptyCascadeToOpen() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveToSameColorCascadePileFromCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 4);
  }

  @Test
  public void testMoveToFullCascadePileFromCascadePile() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 45, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 43, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 35, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 29, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 21, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 19, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 13, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 51);
    modelTest.move(PileType.CASCADE, 3, 0, PileType.CASCADE, 51);
    assertEquals(13, modelTest.getNumCardsInCascadePile(51));
    assertEquals(52, modelTest.getNumCascadePiles());
    assertEquals(0, modelTest.getNumCardsInCascadePile(3));
    assertEquals(0, modelTest.getNumCardsInCascadePile(5));
    assertEquals(0, modelTest.getNumCardsInCascadePile(11));
    assertEquals(0, modelTest.getNumCardsInCascadePile(13));
    assertEquals(0, modelTest.getNumCardsInCascadePile(19));
    assertEquals(0, modelTest.getNumCardsInCascadePile(21));
    assertEquals(0, modelTest.getNumCardsInCascadePile(27));
    assertEquals(0, modelTest.getNumCardsInCascadePile(29));
    assertEquals(0, modelTest.getNumCardsInCascadePile(35));
    assertEquals(0, modelTest.getNumCardsInCascadePile(37));
    assertEquals(0, modelTest.getNumCardsInCascadePile(43));
    assertEquals(0, modelTest.getNumCardsInCascadePile(45));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmptyOpenToCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.OPEN, 0, 0, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmptyFOUNDATIONtoOpen() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFoundationToOpen() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFoundationToCascade() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationToFoundation() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest
        .move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeToFoundationWrongRank() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeToFoundationWrongRank1() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeToFoundationPileNotAce() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOpenToEmptyFoundationNonAce() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundFoundationPile() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundCascadePile() {
    modelTest.startGame(deck, 10, 4, false);
    modelTest.move(PileType.CASCADE, 20, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundOpenDestination() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundOpenSource() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTest.move(PileType.OPEN, 10, 0, PileType.FOUNDATION, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundSource() {
    modelTest.startGame(deck, 52, 4, false);
    modelTest.move(PileType.CASCADE, -10, 0, PileType.OPEN, 0);
  }

  @Test
  public void testStartGameShuffle() {
    List<Card> deckShuffle = model.getDeck();
    List<Card> testDeck = model.getDeck();
    modelTest.startGame(deckShuffle, 4, 1, true);
    for (int i = 0; i < 13; i++) {

      assertEquals(false, testDeck.get(i * 4)
          .equals(modelTest.getCascadeCardAt(0, i)));
    }
  }

  @Test
  public void testRestartGame() {
    model.startGame(deck, 52, 2, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.startGame(deck, 4, 2, false);
    assertEquals(4, model.getNumCascadePiles());
    assertEquals(0, model.getNumCardsInFoundationPile(0));
    assertEquals(13, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void testGameOver() {
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0,
          PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0,
          PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0,
          PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0,
          PileType.FOUNDATION, 3);
    }
    assertEquals(true, newModel.isGameOver());
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