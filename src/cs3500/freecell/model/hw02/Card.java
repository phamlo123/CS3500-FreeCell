package cs3500.freecell.model.hw02;


/**
 * This class contains information about a card. A card has a Suit (one of four distinct Suits) and
 * a Rank (one of twelve distinct Ranks). Each card is uniqueness is defined by the combination of
 * its Suit and its Rank.
 */
public class Card implements Cards {

  private final SuitType suit;
  private final CardRank rank;
  /**
   * Construct a Card object with two parameters - Suit and Rank.
   *
   * @param suit is an enum representing 1 of 4 predefined Suits
   * @param rank is an enum representing 1 of 13 predefined Ranks
   * @throws IllegalArgumentException if either given Suit or Rank is null
   */

  public Card(SuitType suit, CardRank rank) throws IllegalArgumentException {
    if ((suit == null) || (rank == null)) {
      throw new IllegalArgumentException("card inputs invalid!");
    }
    this.suit = suit;
    this.rank = rank;
  }

  @Override
  public String toString() throws IllegalArgumentException {
    StringBuilder temp = new StringBuilder();
    switch (this.rank) {
      case ACE:
        temp.append("A");
        break;
      case JACK:
        temp.append("J");
        break;
      case QUEEN:
        temp.append("Q");
        break;
      case KING:
        temp.append("K");
        break;
      default:
        temp.append(this.getValue());
    }
    switch (this.suit) {
      case HEARTS:
        temp.append('♥');
        break;
      case DIAMONDS:
        temp.append('♦');
        break;
      case SPADES:
        temp.append('♠');
        break;
      case CLUBS:
        temp.append('♣');
        break;
      default:
        throw new IllegalArgumentException("Invalid SuitType");
    }
    return temp.substring(0);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Card)) {
      return false;
    } else {
      Card temp = (Card) that;
      return (temp.suit == this.suit) && (temp.rank == this.rank);
    }
  }

  @Override
  public int hashCode() {
    int tempForSuit;
    switch (suit) {
      case DIAMONDS:
        tempForSuit = 15;
        break;
      case HEARTS:
        tempForSuit = 16;
        break;
      case SPADES:
        tempForSuit = 17;
        break;
      case CLUBS:
        tempForSuit = 18;
        break;
      default:
        tempForSuit = 0;
    }
    int tempForRank;
    switch (rank) {
      case JACK:
        tempForRank = 111;
        break;
      case QUEEN:
        tempForRank = 121;
        break;
      case KING:
        tempForRank = 131;
        break;
      case ACE:
        tempForRank = 1;
        break;
      default:
        tempForRank = this.getValue();
    }
    return tempForSuit * tempForRank;
  }

  @Override
  public int compareValue(Card other) {
    return Integer.compare(this.getValue(), other.getValue());
  }

  @Override
  public boolean sameColor(Card other) {
    return this.getColor() == other.getColor();
  }

  @Override
  public Color getColor() {
    Color c = null;
    switch (this.suit) {
      case HEARTS:
      case DIAMONDS:
        c = Color.RED;
        break;
      case CLUBS:
      case SPADES:
        c = Color.BLACK;
        break;
      default:
        return c;
    }
    return c;
  }

  @Override
  public boolean valueOneAbove(Card other) {
    return (this.getValue() - 1 == (other.getValue()));
  }

  @Override
  public int getValue() {
    int value = 0;
    switch (this.rank) {
      case ACE:
        value = 1;
        break;
      case TWO:
        value = 2;
        break;
      case THREE:
        value = 3;
        break;
      case FOUR:
        value = 4;
        break;
      case FIVE:
        value = 5;
        break;
      case SIX:
        value = 6;
        break;
      case SEVEN:
        value = 7;
        break;
      case EIGHT:
        value = 8;
        break;
      case NINE:
        value = 9;
        break;
      case TEN:
        value = 10;
        break;
      case JACK:
        value = 11;
        break;
      case QUEEN:
        value = 12;
        break;
      case KING:
        value = 13;
        break;
      default:
        return value;
    }
    return value;
  }

  @Override
  public CardRank getRank() {
    return this.rank;
  }

  @Override
  public SuitType getSuit() {
    return this.suit;
  }

}
