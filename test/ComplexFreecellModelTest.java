
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SuitType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * This class contains tests for ComplexFreecellModel class.
 */

public class ComplexFreecellModelTest {


  FreecellModel<Card> model = FreecellModelCreator.create(GameType.MULTIMOVE);
  FreecellModel<Card> simpleModel = FreecellModelCreator.create(GameType.SINGLEMOVE);
  List<Card> deck = model.getDeck();

  @Test
  public void testMoveMultiple2() {
    model.startGame(deck, 13, 2, false);
    model.move(PileType.CASCADE, 3, 3, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 11);

    assertEquals(6, model.getNumCardsInCascadePile(11));
    assertEquals(3, model.getNumCardsInCascadePile(3));
    assertEquals(3, model.getNumCardsInCascadePile(6));
    assertEquals(0, model.getNumCardsInOpenPile(0));
    assertEquals(0, model.getNumCardsInOpenPile(1));
    assertEquals(0, model.getNumCardsInFoundationPile(0));
    assertEquals(0, model.getNumCardsInFoundationPile(1));
    assertEquals(0, model.getNumCardsInFoundationPile(2));
    assertEquals(0, model.getNumCardsInFoundationPile(3));
  }

  @Test
  public void testMoveMultipleFullOpen() {
    model.startGame(deck, 13, 2, false);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 3, PileType.CASCADE, 6);
    try {
      model.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 11);
    } catch (IllegalArgumentException e) {
      assertEquals("None of the cascade pile is empty and non of the open pile is free",
          e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMultipleFullOpen2() {
    model.startGame(deck, 13, 2, false);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 3, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 11);
  }


  @Test
  public void testMoveMultipleThree() {
    model.startGame(deck, 13, 2, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);

    assertEquals(7, model.getNumCardsInCascadePile(9));
    assertEquals(3, model.getNumCardsInCascadePile(0));
    assertEquals(3, model.getNumCardsInCascadePile(1));
    assertEquals(3, model.getNumCardsInCascadePile(7));
    assertEquals(0, model.getNumCardsInOpenPile(0));
    assertEquals(0, model.getNumCardsInOpenPile(1));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveMultipleMoreThanAvaialble() {
    model.startGame(deck, 13, 1, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);

  }

  @Test
  public void testMoveMultipleMoreThanAvaialbleMessage() {
    model.startGame(deck, 13, 1, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    try {
      model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    } catch (IllegalArgumentException e) {
      assertEquals("the number of cards to be moved is greater than"
          + "the maximum theoretical number of cards that could "
          + "be moved based on # of free piles", e.getMessage());
    }
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
  public void testIncorrectDeck2() {
    deck.remove(0);
    model.startGame(deck, 13, 2, false);
  }

  @Test
  public void testFinishingGame() {
    model.startGame(deck, 52, 2, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i * 4, 0,
          PileType.FOUNDATION, 0);
      model.move(PileType.CASCADE, i * 4 + 1, 0,
          PileType.FOUNDATION, 1);
      model.move(PileType.CASCADE, i * 4 + 2, 0,
          PileType.FOUNDATION, 2);
      model.move(PileType.CASCADE, i * 4 + 3, 0,
          PileType.FOUNDATION, 3);
    }
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void testMoveAllPossibleCardsToCasCade() {
    model.startGame(deck, 53, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 8);
    model.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 16);
    model.move(PileType.CASCADE, 16, 0, PileType.CASCADE, 22);
    model.move(PileType.CASCADE, 22, 0, PileType.CASCADE, 24);
    model.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 30);
    model.move(PileType.CASCADE, 30, 0, PileType.CASCADE, 32);
    model.move(PileType.CASCADE, 32, 0, PileType.CASCADE, 38);
    model.move(PileType.CASCADE, 38, 0, PileType.CASCADE, 40);
    model.move(PileType.CASCADE, 40, 0, PileType.CASCADE, 46);
    model.move(PileType.CASCADE, 46, 0, PileType.CASCADE, 48);

    assertEquals(13, model.getNumCardsInCascadePile(48));
    assertEquals(0, model.getNumCardsInCascadePile(0));
    assertEquals(0, model.getNumCardsInCascadePile(6));
    assertEquals(0, model.getNumCardsInCascadePile(8));
    assertEquals(0, model.getNumCardsInCascadePile(14));


  }

  @Test
  public void testMoveAllPossibleCardsToCasCadeTotEmpty() {
    model.startGame(deck, 53, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 8);
    model.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 16);
    model.move(PileType.CASCADE, 16, 0, PileType.CASCADE, 22);
    model.move(PileType.CASCADE, 22, 0, PileType.CASCADE, 24);
    model.move(PileType.CASCADE, 24, 0, PileType.CASCADE, 30);
    model.move(PileType.CASCADE, 30, 0, PileType.CASCADE, 32);
    model.move(PileType.CASCADE, 32, 0, PileType.CASCADE, 38);
    model.move(PileType.CASCADE, 38, 0, PileType.CASCADE, 40);
    model.move(PileType.CASCADE, 40, 0, PileType.CASCADE, 46);
    model.move(PileType.CASCADE, 46, 0, PileType.CASCADE, 48);

    model.move(PileType.CASCADE, 48, 0, PileType.CASCADE, 52);

    assertEquals(13, model.getNumCardsInCascadePile(52));
    assertEquals(0, model.getNumCardsInCascadePile(48));
  }

  @Test
  public void moveOpen() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    assertEquals(1, model.getNumCardsInOpenPile(0));
    assertEquals(0, model.getNumCardsInCascadePile(0));
  }


  @Test
  public void testStartGameShuffle2() {
    model.startGame(model.getDeck(), 52, 4, true);
    int count = 0;
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i).equals(model.getCascadeCardAt(i, 0))) {
        count++;
      }
    }
    assertFalse(count > 10);
  }

  @Test
  public void testNotCompatibleWithLastCardSameColor() {
    model.startGame(model.getDeck(), 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    try {
      model.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 9);

    } catch (IllegalArgumentException e) {
      assertEquals("The top card is not compatible with the last card"
          + "at the destination pile", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotCompatibleWithLastCardDifferentColorDifferentRank() {
    model.startGame(model.getDeck(), 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 12);
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveFromEmptyCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 7);
  }

  @Test
  public void testMoveWithoutStartingGame() {
    try {
      model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);
    } catch (IllegalStateException e) {
      assertEquals("The Game has not started yet!", e.getMessage());
    }
  }

  @Test
  public void testLessThan0SourcePile() {
    model.startGame(deck, 52, 4, false);
    try {
      model.move(PileType.CASCADE, -1, 0, PileType.CASCADE, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("The provided source pile index is invalid since "
              + "it is greater than the number of piles of that type or it is less than 0!",
          e.getMessage());
    }
  }

  @Test
  public void testGreaterThanMaxSourcePile() {
    model.startGame(deck, 52, 4, false);
    try {
      model.move(PileType.CASCADE, 53, 0, PileType.CASCADE, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("The provided source pile index is invalid "
              + "since it is greater than the number of piles of that type or it is less than 0!",
          e.getMessage());
    }
  }

  @Test
  public void testGreaterThanMaxOpenPile() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    try {
      model.move(PileType.OPEN, 5, 0, PileType.CASCADE, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("The provided source pile index is invalid "
              + "since it is greater than the number of piles of that type or it is less than 0!",
          e.getMessage());
    }
  }

  @Test
  public void testLessThan0OpenPile() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    try {
      model.move(PileType.OPEN, -1, 0, PileType.CASCADE, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("The provided source pile index is invalid "
              + "since it is greater than the number of piles of that type or it is less than 0!",
          e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThanMaxDestCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 53);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThanMaxDestOpen() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThan0DestOpen() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThan0DestCascade() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThan0Foundation() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThanMaxFoundation() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromFoundation() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMultipleFromFoundation() {
    model.startGame(deck, 52, 4, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);

    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  @Test
  public void testRestartingeGame() {
    model.startGame(deck, 52, 1, false);
    assertEquals(52, model.getNumCascadePiles());
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    assertEquals(1, model.getNumCardsInOpenPile(0));
    model.startGame(deck, 13, 1, false);
    assertEquals(13, model.getNumCascadePiles());
    assertEquals(0, model.getNumCardsInOpenPile(0));
  }

  @Test
  public void testGetNumCardsIncascade() {
    model.startGame(deck, 13, 2, true);
    for (int i = 0; i < 13; i++) {
      assertEquals(4, model.getNumCardsInCascadePile(i));
    }
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 1);
    assertEquals(3, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void testGetNumCascadePile() {
    for (int i = 4; i < 55; i++) {
      model.startGame(deck, i, 4, false);
      assertEquals(i, model.getNumCascadePiles());
    }
  }

  @Test
  public void testGetNumOpenPiles() {
    for (int i = 1; i < 10; i++) {
      model.startGame(deck, 4, i, true);
      assertEquals(i, model.getNumOpenPiles());
    }
  }

  @Test
  public void testGetNumCardsInFoundation() {
    model.startGame(deck, 52, 4, false);
    for (int i = 0; i < 4; i++) {
      assertEquals(0, model.getNumCardsInFoundationPile(i));
    }
  }

  @Test
  public void testGetNumCardsInFoundation2() {
    model.startGame(deck, 52, 4, false);
    for (int i = 0; i < 52; i = i + 4) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
      model.move(PileType.CASCADE, i + 1, 0, PileType.FOUNDATION, 1);
    }
    assertEquals(13, model.getNumCardsInFoundationPile(0));
    assertEquals(13, model.getNumCardsInFoundationPile(1));
    assertEquals(0, model.getNumCardsInFoundationPile(3));
    assertEquals(0, model.getNumCardsInFoundationPile(2));
    assertFalse(model.isGameOver());
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

  @Test
  public void testMoveNotValidBuildButCompatible1() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 10, 3, PileType.OPEN, 0);
    try {
      model.move(PileType.CASCADE, 9, 2, PileType.CASCADE, 10);
    } catch (IllegalArgumentException e) {
      assertEquals("The selected bunch of cards "
          + "to be moved is not a valid build!", e.getMessage());
    }
  }


  @Test
  public void testMoveValidBuildButNotCompatibleWithLastCardSameColor() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 10, 3, PileType.OPEN, 0);

    try {
      model.move(PileType.CASCADE, 9, 4, PileType.CASCADE, 12);
    } catch (IllegalArgumentException e) {
      assertEquals("The top card is not compatible with the last card"
          + "at the destination pile", e.getMessage());
    }
  }

  @Test
  public void testMoveValidBuildButNotCompatibleWithLastCardDifferentColorWrongRank() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 10, 3, PileType.OPEN, 0);

    try {
      model.move(PileType.CASCADE, 9, 4, PileType.CASCADE, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("The top card is not compatible with the last card"
          + "at the destination pile", e.getMessage());
    }
  }

  @Test
  public void testMoveValidBuildButNotCompatibleWithLastCardDifferentColorWrongRank2() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 10, 3, PileType.OPEN, 0);

    try {
      model.move(PileType.CASCADE, 9, 4, PileType.CASCADE, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("The top card is not compatible with the last card"
          + "at the destination pile", e.getMessage());
    }
  }

  @Test
  public void testMoveValidBuildButNotCompatibleWithLastCard() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    model.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
    model.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 10, 3, PileType.OPEN, 0);

    try {
      model.move(PileType.CASCADE, 9, 4, PileType.CASCADE, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("The top card is not compatible with the last card"
          + "at the destination pile", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNotValidBuild() {
    model.startGame(deck, 13, 5, false);
    model.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 2);
  }


  @Test
  public void testSimpleModel() {
    simpleModel.startGame(deck, 13, 5, false);
    simpleModel.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 1);
    try {
      simpleModel.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("the provided card to be moved is not the last card in the source pile",
          e.getMessage());
    }
  }


}




