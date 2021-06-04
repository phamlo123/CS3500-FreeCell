package cs3500.freecell.model.hw04;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends the SimpleFreecellModel class and implements the interface FreecellModel for
 * Card and it contains information about the model of the game Freecell, which includes rules for
 * moves user can make, cards and piles of the game Freecell. It inherits all members of the
 * SimplefreecellModel and also supports the multi-card move functionality of the game Freecell.
 */
public class ComplexFreecellModel extends SimpleFreecellModel implements FreecellModel<Card> {

  private final List<Card> temporaryForMoving;

  /**
   * Construct an object for the ComplexFreecellModel class by calling the constructor of the super
   * class SimpleFreecellModel and create a new ArrayList for temporaryForMoving.
   */
  public ComplexFreecellModel() {
    super();
    this.temporaryForMoving = new ArrayList<>();
  }

  /**
   * Support all the functionalities as defined in the super class SimpleFreecellModel, and add a
   * new function for moving multiple cards from a cascade pile to a different cascade pile if all
   * conditions are met.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link PileType})
   * @throws IllegalStateException    if the game has not started.
   */

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {
    try {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    } catch (IllegalArgumentException e) {
      int numEmptyCascade = this.checkNumEmptyCascadeOrOpenPile(PileType.CASCADE);
      int numFreeOpen = this.checkNumEmptyCascadeOrOpenPile(PileType.OPEN);
      int maxNumberOfCardsMovable = (int) ((numFreeOpen + 1)
          * Math.pow(2, numEmptyCascade));
      if (e.getMessage().equals("the provided card to be moved is not the last "
          + "card in the source pile")) {
        if ((numFreeOpen == 0) && (numEmptyCascade == 0)) {
          throw new IllegalArgumentException("None of the cascade pile is empty and non of "
              + "the open pile is free");
        } else {
          moveMulti(pileNumber, cardIndex, destPileNumber, maxNumberOfCardsMovable);
        }
      } else {
        throw e;
      }
    }
  }

  /**
   * Determine the number of empty Cascade OR Open pile in this model.
   *
   * @return the number of empty Cascade pile in this model.
   */
  private int checkNumEmptyCascadeOrOpenPile(PileType pileType) {
    int temp = 0;
    if (pileType == PileType.CASCADE) {
      for (int i = 0; i < super.getNumCascadePiles(); i++) {
        if (super.getNumCardsInCascadePile(i) == 0) {
          temp = temp + 1;
        }
      }
      return temp;
    } else if (pileType == PileType.OPEN) {
      for (int i = 0; i < super.getNumOpenPiles(); i++) {
        if (super.getNumCardsInOpenPile(i) == 0) {
          temp = temp + 1;
        }
      }
      return temp;
    } else {
      return temp;
    }
  }

  /**
   * Helper method for moving multiple cards from a cascade pile to a different cascade pile.
   *
   * @param pileNumber    the index of the source cascade pile.
   * @param cardIndex     the index of the top card where the list of cards to be moved begins
   * @param desPileNumber the index of the destination cascade pile.
   * @param maxNumCards   the maximum number of cards that can theoretically me moved based on the
   *                      number of empty cascade piles and the number of free open piles.
   * @throws IllegalArgumentException if the number of cards to be moved is greater than the max
   *                                  number of cards that can be moved or if the selected list if
   *                                  cards does not form a valid build or if the top card of the
   *                                  list to be moved is not compatible with the last card in the
   *                                  destination pile.
   */
  private void moveMulti(int pileNumber, int cardIndex, int desPileNumber, int maxNumCards)
      throws IllegalArgumentException {
    if ((getNumCardsInCascadePile(pileNumber) - cardIndex) > maxNumCards) {
      throw new IllegalArgumentException("the number of cards to be moved is greater than"
          + "the maximum theoretical number of cards that could be moved based on # of free piles");
    }

    if (!isValidBuild(pileNumber, cardIndex)) {
      throw new IllegalArgumentException("The selected bunch of cards "
          + "to be moved is not a valid build!");
    }

    if (empty(PileType.CASCADE, desPileNumber) && isValidBuild(pileNumber, cardIndex)) {
      executeMoveMulti(pileNumber, cardIndex, desPileNumber);
      return;
    }

    if (!isCompatibleWithLastCard(pileNumber, cardIndex, desPileNumber)) {
      throw new IllegalArgumentException("The top card is not compatible with the last card"
          + "at the destination pile");
    }

    if (isValidBuild(pileNumber, cardIndex)
        && isCompatibleWithLastCard(pileNumber, cardIndex, desPileNumber)) {
      executeMoveMulti(pileNumber, cardIndex, desPileNumber);
    }
  }

  /**
   * Execute the multi-card move from a cascade pile to a different cascade pile.
   *
   * @param pileNumber    the index of the source cascade pile.
   * @param cardIndex     the index of the top card where the list of cards to be moved begins
   * @param desPileNumber the index of the destination cascade pile.
   */
  private void executeMoveMulti(int pileNumber, int cardIndex, int desPileNumber) {
    int lastIndex = super.getNumCardsInCascadePile(pileNumber) - 1;
    for (int i = lastIndex; i > cardIndex - 1; i = i - 1) {
      Card cardToMove = super.getCascadeCardAt(pileNumber, i);
      this.temporaryForMoving.add(cardToMove);
      super.cascadePiles.get(pileNumber).remove(cardToMove);
    }
    for (int i = temporaryForMoving.size() - 1; i > -1; i = i - 1) {
      Card toBeMoved = temporaryForMoving.get(i);
      super.cascadePiles.get(desPileNumber).add(toBeMoved);
      this.temporaryForMoving.remove(toBeMoved);
    }
  }

  /**
   * Determine if the group of cards from the given index to the last index in the given Cascade
   * pile number is valid build, meaning they have alternate Colors and consecutive, decreasing in
   * card value.
   *
   * @param pileNumber is the index of the cascade pile that the list of cards is being evaluated.
   * @param cardIndex  is the starting card index of the top card in the list of cards being
   *                   evaluated.
   * @return a boolean signifying whether the intended list of cards form a valid build.
   */

  private boolean isValidBuild(int pileNumber, int cardIndex) {
    boolean temp = false;
    for (int i = cardIndex; i < super.getNumCardsInCascadePile(pileNumber) - 1; i++) {
      temp = (super.getCascadeCardAt(pileNumber, i)
          .valueOneAbove(super.getCascadeCardAt(pileNumber, i + 1))
          && !(super.getCascadeCardAt(pileNumber, i)
          .sameColor(super.getCascadeCardAt(pileNumber, i + 1))));
    }
    return temp;
  }

  /**
   * Determine if the top card of the list of cards to be moved is compatible with the last card in
   * the destination cascade pile. Compatibility means the top card has rank exactly one below that
   * of the last card and also has a different Color.
   *
   * @param pileNumber         the pile index of the source pile
   * @param cardIndex          the card index of the top card in the source pile
   * @param destinationPileNum the pile index of the destination pile
   * @return a boolean signifying if the top card of the list in the source pile is compatible with
   *         the last card in the destination cascade pile.
   */
  private boolean isCompatibleWithLastCard(int pileNumber, int cardIndex, int destinationPileNum) {
    Card topCard = super.getCascadeCardAt(pileNumber, cardIndex);
    Card lastCardAtDest = super.getCascadeCardAt(destinationPileNum,
        getNumCardsInCascadePile(destinationPileNum) - 1);
    return !topCard.sameColor(lastCardAtDest) && lastCardAtDest.valueOneAbove(topCard);
  }
}