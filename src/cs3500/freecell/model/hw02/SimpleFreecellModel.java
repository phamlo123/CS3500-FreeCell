package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class implements the interface FreecellModel for Card and it contains information about the
 * model of the game Freecell, which includes rules for moves user can make, cards and piles of the
 * game Freecell.
 */

public class SimpleFreecellModel implements FreecellModel<Card> {

  private ArrayList<ArrayList<Card>> openPiles;
  protected ArrayList<ArrayList<Card>> cascadePiles;
  private ArrayList<ArrayList<Card>> foundationPiles;
  private Boolean gameStarted = false;
  private Boolean gameOver = false;

  /**
   * Construct an object for the SimpleFreecellModel class - initializing an empty list of lists for
   * the 3 piles of the game.
   */

  public SimpleFreecellModel() {
    this.openPiles = new ArrayList<>();
    this.cascadePiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
  }

  @Override
  public List<Card> getDeck() {
    SuitType[] allSuits = SuitType.values();
    CardRank[] allValues = CardRank.values();
    List<Card> cardDeck = new ArrayList<>();
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        cardDeck.add(i * 4 + j, new Card(allSuits[j], allValues[i]));
      }
    }
    if (!checkValidDeck(cardDeck)) {
      return null;
    }
    return cardDeck;
  }

  //this method combine multiple followed methods to check if a list of cards is a valid deck
  // (no duplicate cards, one of each of 52 different cards)

  /**
   * This method combine multiple followed methods to check if a list of cards is a valid deck (no
   * duplicate cards, one of each of 52 different cards).
   *
   * @param deck a list of Cards
   * @return a boolean of whether or not this deck is a valid deck of cards
   */
  private boolean checkValidDeck(List<Card> deck) {
    ArrayList<Card> temp = new ArrayList<>(deck);
    return !checkIdentical(deck)
        && (temp.size() == 52)
        && (countSuits(deck, SuitType.DIAMONDS) == 13)
        && (countSuits(deck, SuitType.CLUBS) == 13)
        && (countSuits(deck, SuitType.CLUBS) == 13)
        && (countSuits(deck, SuitType.CLUBS) == 13)
        && (countRanks(deck, CardRank.ACE) == 4)
        && (countRanks(deck, CardRank.TWO) == 4)
        && (countRanks(deck, CardRank.THREE) == 4)
        && (countRanks(deck, CardRank.FOUR) == 4)
        && (countRanks(deck, CardRank.FIVE) == 4)
        && (countRanks(deck, CardRank.SIX) == 4)
        && (countRanks(deck, CardRank.SEVEN) == 4)
        && (countRanks(deck, CardRank.EIGHT) == 4)
        && (countRanks(deck, CardRank.NINE) == 4)
        && (countRanks(deck, CardRank.TEN) == 4)
        && (countRanks(deck, CardRank.JACK) == 4)
        && (countRanks(deck, CardRank.QUEEN) == 4)
        && (countRanks(deck, CardRank.KING) == 4);
  }

  /**
   * Count number of cards of the same given suit in the given list of cards.
   *
   * @param deck the given deck that will be iterated on.
   * @param s    A given SuitType that will be the comparator
   * @return the number of cards of the same given suit in the deck.
   */

  private int countSuits(List<Card> deck, SuitType s) {
    int count = 0;
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i).getSuit() == s) {
        count++;
      }
    }
    return count;
  }

  /**
   * Count number of cards of the same given rank in the given list of cards.
   *
   * @param deck the given deck that will be iterated on.
   * @param c    A given Card Rank that will be the comparator
   * @return the number of cards of the same given rank in the deck.
   */
  private int countRanks(List<Card> deck, CardRank c) {
    int count = 0;
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i).getRank() == c) {
        count++;
      }
    }
    return count;
  }

  /**
   * Check of a given list of cards has two identical cards.
   *
   * @param deck List of Cards
   * @return a boolean whether or not a given List has two identical items.
   */
  private boolean checkIdentical(List<Card> deck) {
    for (int i = 0; i < deck.size() - 1; i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if ((deck.get(i)).equals(deck.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  //Calling StartGame while the game is not over will restart the game
  // (deal new deck of cards and reset the foundation piles and the open piles)
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    List<Card> tempDeckArray = new ArrayList<>(deck);
    if (!checkValidDeck(tempDeckArray) || (numCascadePiles < 4) || (numOpenPiles < 1)) {
      throw new IllegalArgumentException("Provided inputs are not valid");
    } else {
      if (shuffle) {
        Collections.shuffle(tempDeckArray);

      }
      cascadePiles = createCascadePiles(tempDeckArray, numCascadePiles);
      openPiles = createOpenPiles(numOpenPiles);
      foundationPiles = createFoundationPiles();
      //Update that the game has started
      gameStarted = true;
    }
  }

  /**
   * Initialize List of List of Cards that represent Cascade Piles, with each pile having a number
   * of cards. The cards in each sublist are indexed (and distributed from the deck) in a
   * round-robin fashion.
   *
   * @param deck            Deck of Cards in the form of List<Card></Card>
   * @param numCascadePiles given number of Cascade Piles
   * @return
   */
  private ArrayList<ArrayList<Card>> createCascadePiles(List<Card> deck, int numCascadePiles) {
    int roundUpDivision = (int) Math.ceil(52.00 / (double) numCascadePiles);
    List<Card> tempDeckArray = new ArrayList<>(deck);
    //Initialize the List of Lists for casCadePiles
    ArrayList<ArrayList<Card>> tempListOfLists = new ArrayList<>();
    //Add all items from deck to sublists
    if ((52 % numCascadePiles) == 0) {
      for (int row = 0; row < numCascadePiles; row++) {
        tempListOfLists.add(new ArrayList<>());
        for (int col = 0; col < roundUpDivision; col++) {
          tempListOfLists.get(row).add(col, tempDeckArray.get(col * numCascadePiles + row));
        }
      }
    } else {
      for (int i = 0; i < numCascadePiles; i++) {
        tempListOfLists.add(new ArrayList<>());
      }
      //Add items from deck to sublists, ignoring items at the largest index in all sublists
      for (int row = 0; row < numCascadePiles; row++) {
        for (int col = 0; col < roundUpDivision - 1; col++) {
          tempListOfLists.get(row).add(col, tempDeckArray.get(col * numCascadePiles + row));
        }
      }
      //Add remaining items from the deck to sublists
      for (int i = 0; i < (52 % numCascadePiles); i++) {
        tempListOfLists.get(i).add(roundUpDivision - 1,
            tempDeckArray.get(numCascadePiles * (roundUpDivision - 1) + i));
      }
    }
    return tempListOfLists;
  }

  /**
   * Create a List of Lists of Cards that represent Open Piles.
   *
   * @param numOpenPiles number of open piles
   * @return A list of Lists of Cards that represent Open Piles and the cards in those piles
   */
  private ArrayList<ArrayList<Card>> createOpenPiles(int numOpenPiles) {
    // Initalize List of Lists for openPiles
    ArrayList<ArrayList<Card>> tempListOfListOpen = new ArrayList<>();
    for (int i = 0; i < numOpenPiles; i++) {
      tempListOfListOpen.add(new ArrayList<>());
    }
    return tempListOfListOpen;
  }

  /**
   * Initialize a List of Lists of Cards that represent Foundation Piles.
   *
   * @return A list of 4 Lists of Cards that represent Open Piles and the cards in those piles, each
   *         sublist has no cards in them
   */
  private ArrayList<ArrayList<Card>> createFoundationPiles() {
    //Initialize List of Lists for foundationPiles
    ArrayList<ArrayList<Card>> tempListOfListFoundation = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      tempListOfListFoundation.add(new ArrayList<>());
    }
    return tempListOfListFoundation;
  }


  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The Game has not started yet!");
    } else if ((pileNumber > getNumPiles(source) - 1) || (pileNumber < 0)) {
      throw new IllegalArgumentException("The provided source pile index is invalid "
          + "since it is greater than the number of piles of that type or it is less than 0!");
    } else if (empty(source, pileNumber)) {
      throw new IllegalArgumentException("The provided source is empty!");
    } else if ((destPileNumber > getNumPiles(destination) - 1) || (destPileNumber < 0)) {
      throw new IllegalArgumentException("The provided destination pile index is invalid since "
          + "greater than the number of piles of that type or it is less than 0!");
    } else if (isFull(destination, destPileNumber)) {
      throw new IllegalArgumentException("The provided destination pile is full!");
    } else {
      switch (source) {
        case CASCADE:
          moveCascade(pileNumber, cardIndex, destination, destPileNumber);
          break;
        case OPEN:
          moveOpen(pileNumber, destination, destPileNumber);
          break;
        default:
          throw new IllegalArgumentException("move is illegal!");
      }
    }
    if (this.isGameOver()) {
      this.gameOver = true;
    }
  }

  /**
   * Get number of piles of certain type of this model.
   * @param pileType the type of the pile
   * @return the number of piles of the given type of this model
   */
  private int getNumPiles(PileType pileType) {
    switch (pileType) {
      case OPEN:
        return this.getNumOpenPiles();
      case FOUNDATION:
        return 4;
      case CASCADE:
        return this.getNumCascadePiles();
      default:
        return 0;
    }
  }

  /**
   * helper method to move a card from a Cascade pile to a different pile, subject to game rules.
   *
   * @param cascadePileNumber index of source cascade Pile
   * @param cardIndex         index of card to be moved
   * @param destination       PileType of destination pile
   * @param destPileNumber    index of the destination pile
   * @throws IllegalArgumentException if move does not follow game rules or one or more indices are
   *                                  invalid
   */
  private void moveCascade(int cascadePileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {
    Card toBeMoved = getCascadeCardAt(cascadePileNumber, cardIndex);
    switch (destination) {
      case OPEN:
        if (isFull(destination, destPileNumber)) {
          throw new IllegalArgumentException("the destination Open Pile is full");
        } else {
          cascadePiles.get(cascadePileNumber).remove(toBeMoved);
          openPiles.get(destPileNumber).add(toBeMoved);
        }
        break;
      case CASCADE:
        if (isLastIndexCard(PileType.CASCADE, cascadePileNumber, cardIndex)) {
          if (!empty(destination, destPileNumber)) {
            Card lastCardAtDestCascade = getCascadeCardAt(destPileNumber,
                getNumCardsInCascadePile(destPileNumber) - 1);
            if (!(lastCardAtDestCascade.valueOneAbove(toBeMoved))
                || toBeMoved.sameColor(lastCardAtDestCascade)) {
              throw new IllegalArgumentException("The card to be moved is either the same color "
                  + "as the last card in pile or does not have value exactly "
                  + "one below it");
            } else {
              cascadePiles.get(destPileNumber).add(toBeMoved);
              cascadePiles.get(cascadePileNumber).remove(toBeMoved);
            }
          } else {
            cascadePiles.get(destPileNumber).add(toBeMoved);
            cascadePiles.get(cascadePileNumber).remove(toBeMoved);
          }
        } else {
          throw new IllegalArgumentException("the provided card to be moved is not the last "
              + "card in the source pile");
        }
        break;
      case FOUNDATION:
        if (!empty(destination, destPileNumber)) {
          Card lastCardAtDestFoundation = getFoundationCardAt(destPileNumber,
              getNumCardsInFoundationPile(destPileNumber) - 1);
          if (toBeMoved.getSuit() != lastCardAtDestFoundation.getSuit()
              || !toBeMoved.valueOneAbove(lastCardAtDestFoundation)) {
            throw new IllegalArgumentException("Move is Illegal because provided moved card suit "
                + "does not match suit at foundation pile or cardRank is not exactly one above "
                + "that of the last card in Foundation Pile");
          } else {
            cascadePiles.get(cascadePileNumber).remove(toBeMoved);
            foundationPiles.get(destPileNumber).add(toBeMoved);
          }
        } else if ((toBeMoved.getRank() == CardRank.ACE)) {
          cascadePiles.get(cascadePileNumber).remove(toBeMoved);
          foundationPiles.get(destPileNumber).add(toBeMoved);
        } else {
          throw new IllegalArgumentException("Move is illegal because the provided card is not "
              + "an ACE so it cannot be moved into an empty Foundation Pile");
        }
        break;
      default:
        throw new IllegalArgumentException("move is Illegal");
    }
  }


  /**
   * helper method to move a card from an open pile to a different pile of different types, subject
   * to game rules.
   *
   * @param pileNumber     index of source pile
   * @param destination    PileType of destination pile
   * @param destPileNumber Index of destination pile
   * @throws IllegalArgumentException if move does not follow game rules or one or more indices are
   *                                  invalid or the pile type is not valid
   */
  private void moveOpen(int pileNumber, PileType destination,
      int destPileNumber) throws IllegalArgumentException {
    Card toBeMoved = getOpenCardAt(pileNumber);
    switch (destination) {
      case OPEN:
        if (isFull(destination, destPileNumber)) {
          throw new IllegalArgumentException("the destination Open Pile is full");
        } else if (pileNumber == destPileNumber) {
          throw new IllegalArgumentException("The source and destination pile are the same pile!");
        } else {
          openPiles.get(pileNumber).remove(toBeMoved);
          openPiles.get(destPileNumber).add(toBeMoved);
        }
        break;
      case FOUNDATION:
        if (!empty(destination, destPileNumber)) {
          Card lastCardAtDestFoundation = getFoundationCardAt(destPileNumber,
              getNumCardsInFoundationPile(destPileNumber) - 1);
          if (!toBeMoved.valueOneAbove(lastCardAtDestFoundation)
              || (toBeMoved.getSuit() != lastCardAtDestFoundation.getSuit())) {
            throw new IllegalArgumentException("Move is Illegal because provided moved card suit "
                + "does not match suit at foundation pile or cardRank is not exactly one above "
                + "that of the last card in Foundation Pile");
          } else {
            openPiles.get(pileNumber).remove(toBeMoved);
            foundationPiles.get(destPileNumber).add(toBeMoved);
          }
        } else if (toBeMoved.getRank() == CardRank.ACE) {
          openPiles.get(pileNumber).remove(toBeMoved);
          foundationPiles.get(destPileNumber).add(toBeMoved);
        } else {
          throw new IllegalArgumentException("Move is illegal because the provided card is not "
              + "an ACE so it cannot be moved into an empty Foundation Pile");
        }
        break;
      case CASCADE:
        if (pileNumber == destPileNumber) {
          throw new IllegalArgumentException("Source and Destination Piles are the same pile!");
        } else if (!empty(destination, destPileNumber)) {
          Card lastCardAtDestCascade = getCascadeCardAt(destPileNumber,
              getNumCardsInCascadePile(destPileNumber) - 1);
          if (!(lastCardAtDestCascade.valueOneAbove(toBeMoved))
              || toBeMoved.sameColor(lastCardAtDestCascade)) {
            throw new IllegalArgumentException("Move is Illegal because either"
                + " the cardRank of the last card in the"
                + " destination pile is not exactly "
                + " one above the card to be moved or they have the same color");
          } else {
            cascadePiles.get(destPileNumber).add(toBeMoved);
            openPiles.get(pileNumber).remove(toBeMoved);
          }
        } else {
          cascadePiles.get(destPileNumber).add(toBeMoved);
          openPiles.get(pileNumber).remove(toBeMoved);
        }
        break;
      default:
        throw new IllegalArgumentException("move is Illegal");
    }
  }

  //

  /**
   * Check if the given pile is full.
   *
   * @param pileType PileType of the pile
   * @param index    of the pile
   * @return a boolean telling whether or not a pile is full
   * @throws IllegalArgumentException if the index or the pile type is invalid
   */
  private boolean isFull(PileType pileType, int index) throws IllegalArgumentException {
    boolean a;
    switch (pileType) {
      case OPEN:
        a = (openPiles.get(index).size() == 1);
        break;
      case CASCADE:
        a = (cascadePiles.get(index).size() == 13);
        break;
      case FOUNDATION:
        a = (foundationPiles.get(index).size() == 13);
        break;
      default:
        throw new IllegalArgumentException("Provided Index is not Valid");
    }
    return a;
  }


  /**
   * Check if the given pile is empty.
   *
   * @param pileType type of the parameter pile
   * @param index    index of the pile
   * @return a boolean telling whether or not th pile is full
   * @throws IllegalArgumentException if the index or the pile type is not valid
   */
  protected boolean empty(PileType pileType, int index) throws IllegalArgumentException {
    boolean a;
    switch (pileType) {
      case OPEN:
        a = (openPiles.get(index).isEmpty());
        break;
      case FOUNDATION:
        a = (foundationPiles.get(index).isEmpty());
        break;
      case CASCADE:
        a = (cascadePiles.get(index).isEmpty());
        break;
      default:
        throw new IllegalArgumentException("Provided index is not valid");
    }
    return a;
  }

  /**
   * Check if the given card index is the last index in a given pile.
   *
   * @param pileType  Pile Type of the pole
   * @param pileIndex index of the pile
   * @param cardIndex index of the card in that pile
   * @return whether or not the card in that index in that pile is the last card in that pile
   * @throws IllegalArgumentException if either the parameters are not valid
   */
  private boolean isLastIndexCard(PileType pileType, int pileIndex, int cardIndex)
      throws IllegalArgumentException {
    switch (pileType) {
      case FOUNDATION:
        return getNumCardsInFoundationPile(pileIndex) == (cardIndex + 1);
      case CASCADE:
        return getNumCardsInCascadePile(pileIndex) == (cardIndex + 1);
      case OPEN:
        return cardIndex == 1;
      default:
        throw new IllegalArgumentException("provided index is invalid");
    }
  }


  @Override
  public boolean isGameOver() {
    if (gameStarted) {
      return ((foundationPiles.get(0).size() == 13)
          && (foundationPiles.get(1).size() == 13)
          && (foundationPiles.get(2).size() == 13)
          && (foundationPiles.get(3).size() == 13));
    } else {
      return false;
    }
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started!");
    }
    try {
      return foundationPiles.get(index).size();
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The provided foundation pile index is invalid!");
    }
  }

  @Override
  public int getNumCascadePiles() {
    if (!gameStarted) {
      return -1;
    }
    return cascadePiles.size();
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started!");
    }
    try {
      return cascadePiles.get(index).size();
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The provided Cascade pile index is not valid!");
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started yet!");
    }
    try {
      return openPiles.get(index).size();
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The provided Open Pile index is not valid!");
    }
  }

  @Override
  public int getNumOpenPiles() {
    if (!gameStarted) {
      return -1;
    } else {
      return openPiles.size();
    }
  }


  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started!");
    }
    if ((pileIndex < 0) || (pileIndex > 3)) {
      throw new IllegalArgumentException("The provided foundation pile index is not valid!");
    }
    if ((cardIndex < 0) || (cardIndex > (getNumCardsInFoundationPile(pileIndex) - 1))) {
      throw new IllegalArgumentException("The provided Foundation card index is not valid!");
    }
    return foundationPiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started");
    } else if ((pileIndex < 0) || (pileIndex > getNumCascadePiles() - 1)) {
      throw new IllegalArgumentException("The provided Cascade pile index is not valid");
    } else if ((cardIndex < 0) || (cardIndex > getNumCardsInCascadePile(pileIndex) - 1)) {
      throw new IllegalArgumentException("The provided Cascade card index is not valid");
    } else {
      return cascadePiles.get(pileIndex).get(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("The game has not started");
    } else if ((pileIndex > getNumOpenPiles() - 1) || (pileIndex < 0)) {
      throw new IllegalArgumentException("The provided Open pile index is not valid");
    } else if (openPiles.get(pileIndex).size() == 0) {
      return null;
    } else {
      return openPiles.get(pileIndex).get(0);
    }
  }
}



